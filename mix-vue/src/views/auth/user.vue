<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>用户管理</a-breadcrumb-item>
    </a-breadcrumb>
    <a-button type="default" style="margin: 10px">新增用户</a-button>
    <a-table
      :columns="columns"
      :row-key="(record) => record.login.uuid"
      :data-source="dataSource"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
    >
      <template #name="{ text }">{{ text.first }} {{ text.last }}</template>
    </a-table>
  </a-layout-content>
</template>
<script lang="ts">
import { defineComponent, ref, computed } from "vue";
import { usePagination } from "vue-request";
import { getUserList } from "@/api/admin/user";
const columns = [
  {
    title: "用户名",
    dataIndex: "name",
    sorter: true,
    width: "20%",
    slots: {
      customRender: "name",
    },
  },
  {
    title: "Email",
    dataIndex: "email",
  },
  {
    title: "手机号码",
    width: "20%",
  },

  {
    title: "最近登录日期",
    width: "20%",
  },
  {
    title: "备注",
    width: "20%",
  },
];

const queryData = (params: any) => {
  return getUserList(params);
};

export default defineComponent({
  setup() {
    const { data: dataSource, run, loading, current, pageSize } = usePagination(
      queryData,
      {
        formatResult: (res: any) => res.results,
        pagination: {
          currentKey: "page",
          pageSizeKey: "results",
        },
      }
    );

    const pagination = computed(() => ({
      total: 200,
      current: current.value,
      pageSize: pageSize.value,
    }));

    const handleTableChange = (
      pag: { pageSize: any; current: any },
      filters: any,
      sorter: { field: any; order: any }
    ) => {
      run({
        results: pag.pageSize,
        page: pag?.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    };

    return {
      dataSource,
      pagination,
      loading,
      columns,
      handleTableChange,
    };
  },
});
</script>
<style>
</style>