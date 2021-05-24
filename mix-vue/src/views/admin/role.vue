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
        <a-form-item>
          <a-button
              type="primary"
              @click="
              handleQuery({
                page: 1,
                pageSize: pagination.pageSize,
                roleName: param.name,
              })
            "
          >
            查询
          </a-button>
        </a-form-item>
        <a-form-item>
          <a-button @click="resetForm">重置</a-button>
        </a-form-item>
        <a-form-item>
          <a-button type="default" @click="add()"> 新增</a-button>
        </a-form-item>
      </a-form>
    </p>

    <a-table
        :columns="columns"
        :row-key="(record) => record.id"
        :data-source="roleList"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
    >
      <template v-slot:action="{ record }">
        <a-space size="large">
          <a-button type="primary" @click="edit(record)"> 编辑</a-button>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.id)"
          >
            <a-button type="danger"> 删除</a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>
  </a-layout-content>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {pageRole} from "@/api/admin/role";
import {message} from "ant-design-vue";

const param = ref();
const roleList = ref();
const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0,
});
const loading = ref(false);

const columns = [
  {
    title: "角色名称",
    dataIndex: "roleName",
    sorter: true,
    width: "15%",
    slots: {
      customRender: "roleName",
    },
  },
  {
    title: "角色编码",
    width: "15%",
    dataIndex: "roleCode",
  },
  {
    title: "是否有效",
    width: "10%",
    dataIndex: "status",
  },
  {
    title: "操作",
    width: "25%",
    key: "action",
    slots: {
      customRender: "action",
    },
  },
];


export default defineComponent({
  setup() {
    param.value = {};
    const handleQuery = (param: any) => {
      console.log(param)
      loading.value = true;
      roleList.value = [];
      pageRole(param).then((response) => {
        loading.value = false;
        const res = response.data;
        if (res.code === 200) {
          roleList.value = res.data.list;
          // 重置分页按钮
          pagination.value.current = param.page;
          pagination.value.total = res.data.totalSize;
        } else {
          message.error(res.data.message);
          loading.value = false;
        }
      })
    };

    const resetForm = () => {
      param.value.name = "";
      handleQuery({
        page: 1,
        pageSize: pagination.value.pageSize,
      });
    };

    //新增
    const add = async () => {
      console.log()
    };

    // 编辑
    const edit = async (record: any) => {
      console.log(record);
    };

    /**
     * 删除
     */
    const handleDelete = (id: number) => {
      console.log(id);
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      handleQuery({
        page: pagination.current,
        pageSize: pagination.pageSize,
        roleName: param.value.name,
      });
    };

    onMounted(() => {
      handleQuery({
        page: 1,
        pageSize: pagination.value.pageSize,
      });
    });

    return {
      columns,
      param,
      handleQuery,
      pagination,
      resetForm,
      roleList,
      loading,
      handleTableChange,
      add,
      edit,
      handleDelete
    }
  },
})
</script>
<style>

</style>