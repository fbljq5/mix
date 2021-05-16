<template>
  <a-menu theme="dark" v-model:selectedKeys="selectedKeys" mode="inline">
    <a-menu-item key="1" v-if="false">
      <router-link to="/dashboard">仪表盘</router-link>
    </a-menu-item>
    <a-sub-menu key="sub1">
      <template #title>
        <span>
          <user-outlined />
          <span>权限管理</span>
        </span>
      </template>
      <a-menu-item key="2">
        <router-link to="/admin/user">用户管理</router-link>
      </a-menu-item>
      <a-menu-item key="3">角色管理 </a-menu-item>
      <a-menu-item key="4">菜单管理</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub2">
      <template #title>
        <span>
          <eye-outlined />
          <span>监控中心</span>
        </span>
      </template>
      <a-menu-item key="5">服务器监控</a-menu-item>
      <a-menu-item key="6">注册中心监控</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub3">
      <template #title>
        <span>
          <setting-outlined />
          <span>系统管理</span>
        </span>
      </template>
      <a-menu-item key="7">参数配置</a-menu-item>
      <a-menu-item key="8">接口管理</a-menu-item>
      <a-menu-item key="9">日志管理</a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>
<script lang="ts">
import { defineComponent } from "@vue/composition-api";
import {
  UserOutlined,
  SettingOutlined,
  EyeOutlined,
} from "@ant-design/icons-vue";
export default defineComponent({
  name: "MixMenu",
  components: {
    UserOutlined,
    SettingOutlined,
    EyeOutlined,
  },
});
</script>
