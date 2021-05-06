<template>
  <div class="right-options">
    <a-layout-header style="background: #fff; padding: 0">
      <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys"
        :style="{ lineHeight: '64px' }"
      >
        <a @click.prevent="doLogout" class="logoutBtn"
          ><poweroff-outlined /> 退出登录</a
        >
      </a-menu>
    </a-layout-header>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "@vue/composition-api";
import { message, Modal } from "ant-design-vue";
import { logout } from "@/api/admin/user";
import { useRouter, useRoute } from "vue-router";
export default defineComponent({
  name: "MixHeader",

  setup() {
    const router = useRouter();
    const route = useRoute();
    // 退出登录
    const doLogout = () => {
      Modal.confirm({
        title: "您确定要退出登录吗？",
        onOk: () => {
          console.log("OK");
          logout().then((response) => {
            console.log(response);
            const res = response.data;
            if (res.code == 200) {
              message.success("成功退出登录");
              localStorage.setItem("MIX_TOKEN", res.data);
              router
                .replace({
                  name: "login",
                  query: {
                    redirect: route.fullPath,
                  },
                })
                .finally(() => location.reload());
            }else{
              message.error('登出失败');
            }
          });
        },
      });
    };

    return {
      doLogout,
    };
  },
});
</script>
<style lang="less" scoped>
.logoutBtn {
  align-items: center;
  margin-left: 93%;
}
</style>
