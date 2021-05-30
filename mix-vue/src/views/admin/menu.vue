<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>菜单管理</a-breadcrumb-item>
    </a-breadcrumb>

    <p>
      <a-form layout="inline" :model="param" ref="formRef">
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="菜单名称">
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
        :data-source="menuList"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange">
    </a-table>
  </a-layout-content>

</template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch} from "vue";
import {Tool} from "@/utils/tool";

const param = ref();
const menuList = ref();
const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0,
});
const loading = ref(false);

const columns = [
  {
    title: "菜单名称",
    dataIndex: "menuName",
    sorter: true,
    width: "15%",
    slots: {
      customRender: "menuName",
    },
  },
  {
    title: "图标",
    width: "15%",
    dataIndex: "icon",
  },
  {
    title: "排序",
    width: "15%",
    dataIndex: "orderSort",
  },
  {
    title: "状态",
    width: "10%",
    dataIndex: "status",
    slots: {
      customRender: "status",
    },
  },
  {
    title: "路由路径",
    width: "10%",
    dataIndex: "path",
  },
  {
    title: "创建时间",
    width: "20%",
    dataIndex: "gmtCreate",
  },
  {
    title: "操作",
    width: "15%",
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
      menuList.value = [];
      // TODO
    };

    const resetForm = () => {
      param.value.name = "";
      handleQuery({
        page: 1,
        pageSize: pagination.value.pageSize,
      });
    };

    const menu = ref()
    const modelVisible = ref(false);
    const modalLoading = ref(false);

    //新增
    const add = async () => {
      modelVisible.value = true;
      menu.value = {};
    };

    // 编辑
    const edit = async (record: any) => {
      menu.value = "";
      modelVisible.value = true;
      menu.value = Tool.copy(record);
      console.log("record", record.id)
      console.log(record);
    };

    /**
     * 删除
     */
    const handleDelete = (id: number) => {
      // TODO

      console.log(id);
    };

    const handleSaveOrUpdate = () => {
      modalLoading.value = true;
      // TODO
    }

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

    const handleStatusChange = (record: any) => {
      // TODO
    }


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
      menuList,
      loading,
      handleTableChange,
      modelVisible,
      modalLoading,
      add,
      edit,
      handleDelete,
      handleStatusChange,
      handleSaveOrUpdate,
    }
  },
})
</script>
<style>

</style>