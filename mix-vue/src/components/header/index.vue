<template>
  <div class="right-options">
    <a-layout-header style="background: #fff; padding: 0">
      <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys"
        :style="{ lineHeight: '64px' }"
      >
        <a-dropdown :trigger="['click']">
          <a class="ant-dropdown-link" style="margin-left: 95%">{{
            userInfo.userName
          }}</a>
          <template #overlay>
            <a-menu>
              <a-menu-item>
                <a @click.prevent="doLogout">退出登录</a>
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </a-menu>
    </a-layout-header>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive } from "vue";
import { message, Modal } from "ant-design-vue";
import { logout, getUserInfo } from "@/api/admin/user";
import { useRouter, useRoute } from "vue-router";
export default defineComponent({
  name: "MixHeader",

  setup() {
    const router = useRouter();
    const route = useRoute();
    let userInfo = reactive({
      id: 0,
      userName: "",
    });

    getUserInfo().then((response) => {
      let user = response.data.data;
      userInfo.id = user.id;
      userInfo.userName = user.userName;
    });

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
            } else {
              message.error("登出失败");
            }
          });
        },
      });
    };

    return {
      doLogout,
      userInfo,
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
