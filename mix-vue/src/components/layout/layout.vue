<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <mix-logo />
      <mix-menu />
    </a-layout-sider>
    <a-layout>
      <mix-header />
      <router-view />
      <mix-footer />
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MixLogo from "@/components/logo/index.vue";
import MixHeader from "@/components/header/index.vue";
import MixFooter from "@/components/footer/index.vue";
import MixMenu from "@/components/menu/index.vue";
export default defineComponent({
  name: "app",
  components: {
    MixHeader,
    MixFooter,
    MixLogo,
    MixMenu,
  },
});
</script>