<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>仪表盘</a-breadcrumb-item>
    </a-breadcrumb>
    <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
      Hello
    </div>
  </a-layout-content>
</template>
<script lang="ts">
import { defineComponent, ref } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {
      collapsed: ref<boolean>(false),
      selectedKeys: ref<string[]>(["1"]),
    };
  },
});
</script>
<style>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}
</style>