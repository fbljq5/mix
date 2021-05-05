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

export default defineComponent({
  name: "MixHeader",
});
</script>
<style lang="less" scoped>
.logoutBtn {
  align-items: center;
  margin-left: 93%;
}
</style>
