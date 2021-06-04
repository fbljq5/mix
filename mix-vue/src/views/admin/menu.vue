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
                menuName: param.name,
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
    <a-table :columns="columns" :data-source="treeData" :row-key='record=>record.id'>
      <template v-slot:icon="{ record }">
        <div v-text="record.icon">
        </div>
      </template>
      <template v-slot:action="{ record }">
        <a-space size="large">
          <a-button type="default" @click="add(record)"> 新增</a-button>
          <a-button type="primary" @click="edit(record)"> 编辑</a-button>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.id)">
            <a-button type="danger"> 删除</a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>
  </a-layout-content>

</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {Tool} from "@/utils/tool";
import {pageMenu} from "@/api/admin/menu";
import {message} from "ant-design-vue";
import {RotateLeftOutlined  } from '@ant-design/icons-vue';


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
    slots: {
      customRender: "icon",
    },
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
    const treeData = ref();
    param.value = {};

    const handleQuery = (param: any) => {
      console.log(param)
      menuList.value = [];
      // TODO
      pageMenu(param).then((response) => {
        loading.value = false;
        const res = response.data;
        if (res.code == 200) {
          let menuList = res.data.list;
          treeData.value = getJsonTree(menuList, 0)
          console.log("treeData", treeData.value)
          pagination.value.current = param.page;
          pagination.value.total = res.data.totalSize;
        } else {
          message.error(res.msg);
          loading.value = false;
        }
      })
    };

    /**
     * 菜单数据转换成树状结构所需数据
     * @param data
     * @param parentId
     */
    const getJsonTree = (data: any, parentId: any) => {
      let itemArr = [];
      for (let i = 0; i < data.length; i++) {
        let node = data[i];
        if (node.parentId == parentId) {
          let newNode = {
            key: String,
            id: Number,
            menuName: String,
            icon: String,
            orderSort: String,
            status: String,
            path: String,
            gmtCreate: String,
            children: Array<any>()
          };
          newNode.key = node.id;
          newNode.id = node.id;
          newNode.menuName = node.menuName;
          newNode.icon = node.icon;
          newNode.orderSort = node.orderSort;
          newNode.status = node.status;
          newNode.path = node.path;
          newNode.gmtCreate = node.gmtCreate;
          newNode.children = getJsonTree(data, node.id);
          itemArr.push(newNode);
        }
      }
      return itemArr;
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
      console.log(record)
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
      treeData,
    }
  },
})
</script>
<style>

</style>