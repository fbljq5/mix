<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>角色管理</a-breadcrumb-item>
    </a-breadcrumb>

    <p>
      <a-form layout="inline" :model="param" ref="formRef">
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="角色名称">
          </a-input>
        </a-form-item>

  </a-layout-content>
</template>

<script lang="ts">
import {defineComponent, ref} from "vue";


const columns: any = []
const param = ref({});

export default defineComponent({
  setup() {

    const handleQuery = (param: any) => {

    };
    return {
      columns,
      param
    }
  },
})
</script>
<style>

</style>