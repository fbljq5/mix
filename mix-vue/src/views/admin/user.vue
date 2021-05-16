<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>用户管理</a-breadcrumb-item>
    </a-breadcrumb>

    <p>
      <a-form layout="inline" :model="param">
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="名称"> </a-input>
        </a-form-item>
        <a-form-item>
          <a-button
            type="primary"
            @click="
              handleQuery({
                page: 1,
                pageSize: pagination.pageSize,
                username: param.name,
              })
            "
          >
            查询
          </a-button>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="add()"> 新增 </a-button>
        </a-form-item>
      </a-form>
    </p>

    <a-table
      :columns="columns"
      :row-key="(record) => record.id"
      :data-source="userList"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
    >
    </a-table>
  </a-layout-content>
</template>
<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";
import { message } from "ant-design-vue";
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

export default defineComponent({
  setup() {
    const param = ref();
    param.value = {};
    const userList = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0,
    });
    const loading = ref(false);

    const handleQuery = (param: any) => {
      loading.value = true;
      userList.value = [];
      getUserList(param).then((response) => {
        loading.value = false;
        const res = response.data;
        console.log(res);
        if (res.code === 200) {
          userList.value = res.data.list;
          // 重置分页按钮
          pagination.value.current = param.page;
          pagination.value.total = res.data.totalSize;
          console.log(res.totalSize);
        } else {
          message.error(res.data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      handleQuery({
        page: pagination.current,
        pageSize: pagination.pageSize,
        username:param.value.name
      });
    };

    onMounted(() => {
      handleQuery({
        page: 1,
        pageSize: pagination.value.pageSize,
      });
    });

    return {
      pagination,
      loading,
      columns,
      userList,
      handleQuery,
      param,
      handleTableChange,
    };
  },
});
</script>
<style>
</style>