<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>用户管理</a-breadcrumb-item>
    </a-breadcrumb>
    <div>
      <a-button type="default" style="margin: 0 20px 20px 0px"
        >新增用户</a-button
      >
      <a-input-search
        v-model:value="value"
        placeholder="请输入用户名"
        enter-button
        @search="onSearch"
        style="width: 30%; margin: 0 20px 20px 0px"
      />
    </div>
    <a-table
      :columns="columns"
      :row-key="(record) => record.id"
      :data-source="dataSource"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
    >
      <!-- <template #name="{ text }">{{ text.first }} {{ text.last }}</template> -->
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
    dataIndex: "userName",
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
    dataIndex: "phone",
  },

  {
    title: "最近登录时间",
    width: "20%",
    dataIndex: "loginDate",
  },

  {
    title: "创建时间",
    width: "20%",
    dataIndex: "gmtCreate",
  },

  {
    title: "备注",
    width: "20%",
    dataIndex: "remark",
  },
];

// 总的数据数
let total = 0;

let username = ref("");

const queryData = async (params: any) => {
  let res;
  console.log(params);
  await getUserList(params).then((response) => {
    res = response.data.data.list;
    total = response.data.data.totalSize;
  });
  return res;
};

export default defineComponent({
  setup() {
    const { data: dataSource, run, loading, current, pageSize } = usePagination(
      queryData,
      {
        formatResult: (res: any) => res,
        pagination: {
          currentKey: "page",
          pageSizeKey: "pageSize",
        },
      }
    );

    const pagination = computed(() => ({
      total: total,
      current: current.value,
      pageSize: pageSize.value,
    }));

    const handleTableChange = (
      pag: { pageSize: any; current: any },
      filters: any,
      sorter: { field: any; order: any }
    ) => {
      run({
        pageSize: pag.pageSize,
        page: pag?.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    };

    const onSearch = () => {
      handleTableChange;
    };

    return {
      dataSource,
      pagination,
      loading,
      columns,
      handleTableChange,
      onSearch,
    };
  },
});
</script>
<style>
</style>