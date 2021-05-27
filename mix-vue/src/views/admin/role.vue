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
        @change="handleTableChange">

      <template v-slot:status="{ record }">
        <a-switch v-model:checked="record.status" @change="handleStatusChange(record)"/>
      </template>

      <template v-slot:action="{ record }">
        <a-space size="large">
          <a-button type="primary" @click="edit(record)"> 编辑</a-button>
          <a-button type="default" @click="configMenu(record.id)"> 菜单配置</a-button>
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

  <a-modal v-model:visible="modelVisible" title="角色表单" @ok="handleSaveOrUpdate"
           :confirm-loading="modalLoading">
    <a-form :model="role" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="角色名称">
        <a-input v-model:value="role.roleName"></a-input>
      </a-form-item>
      <a-form-item label="角色编码">
        <a-input v-model:value="role.roleCode"></a-input>
      </a-form-item>

      <a-form-item label="显示顺序">
        <a-input v-model:value="role.roleSort"></a-input>
      </a-form-item>
      <a-form-item label="备注">
        <a-input v-model:value="role.remark"></a-input>
      </a-form-item>
    </a-form>
  </a-modal>

  <a-modal v-model:visible="menuModelVisible" title="菜单配置" @ok="handleUpdateMenu()"
           :confirm-loading="menuModalLoading">
    <a-tree checkable
            :tree-data="treeData"
            v-model:expandedKeys="expandedKeys"
            v-model:checkedKeys="checkedKeys">
      <template #title0010><span style="color: #1890ff">sss</span></template>
    </a-tree>
  </a-modal>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch} from "vue";
import {pageRole, addRole, deleteRole, updateRole, switchStatus, assignMenus} from "@/api/admin/role";
import {listMenu} from "@/api/admin/menu";
import {message} from "ant-design-vue";
import {Tool} from "@/utils/tool";

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
    title: "显示顺序",
    width: "15%",
    dataIndex: "roleSort",
  },
  {
    title: "是否有效",
    width: "10%",
    dataIndex: "status",
    slots: {
      customRender: "status",
    },
  },
  {
    title: "创建时间",
    width: "30%",
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

    const role = ref()
    const modelVisible = ref(false);
    const modalLoading = ref(false);

    //新增
    const add = async () => {
      modelVisible.value = true;
      role.value = {};
    };

    // 编辑
    const edit = async (record: any) => {
      role.value = "";
      modelVisible.value = true;
      role.value = Tool.copy(record);
      console.log("record", record.id)
      console.log(record);
    };

    /**
     * 删除
     */
    const handleDelete = (id: number) => {
      deleteRole(id).then(response => {
        loading.value = false;
        let res = response.data;
        if (res.code == 200) {
          message.success("删除成功");
          //成功，冲重刷列表
          handleQuery({
            page: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          //删除失败
          message.error(res.msg);
        }
      })

      console.log(id);
    };

    const handleSaveOrUpdate = () => {
      modalLoading.value = true;
      let promise;
      if (Tool.isNotEmpty(role.value.id)) {
        promise = updateRole(role.value)
      } else {
        promise = addRole(role.value)
      }
      promise.then(response => {
        let res = response.data;
        if (res.code === 200) {
          message.success("操作成功");
          modalLoading.value = false;
          modelVisible.value = false;
          handleQuery({
            page: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(res.msg);
          modalLoading.value = false;
        }
      }).catch(err => {
        modalLoading.value = false;
      })
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
      switchStatus(record).then(response => {
        console.log(response);
        let res = response.data;
        if (res.code == 200) {
          message.success("修改成功")
        } else {
          message.error("修改失败")
        }
      });
    }

    const menuList = ref();
    const menuModelVisible = ref(false);
    const menuModalLoading = ref(false);
    const expandedKeys = ref();
    const checkedKeys = ref();
    const treeData = ref();
    /**
     * 打开菜单配置
     * @param roleId
     */

    const menuIds: any[] = []
    const roleAssignMenusDTO = ref({id: Number, menuIds: menuIds})
    const configMenu = (roleId: any) => {
      roleAssignMenusDTO.value.id = roleId
      menuModelVisible.value = true;
      expandedKeys.value = [];
      checkedKeys.value = [];
      listMenu(roleId).then(response => {
        console.log(response)
        let res = response.data;
        if (res.code == 200) {
          let menuList = res.data;
          treeData.value = getJsonTree(menuList, 0)
          console.log("treeData", treeData.value)
        } else {
          console.log("查询菜单失败!");
        }
        console.log("menuList", menuList);
      })
    }

    /**
     * 菜单数据转换成树状结构所需数据
     * @param data
     * @param parentId
     */
    const getJsonTree = (data: any, parentId: any) => {
      let itemArr = [];
      for (let i = 0; i < data.length; i++) {
        let node = data[i];
        if (node.hasMenu === true) {
          setSelect(node.id)
        }
        if (node.parentId == parentId) {
          let newNode = {
            title: String,
            key: String,
            children: Array<any>()
          };
          newNode.title = node.menuName;
          newNode.key = node.id;
          newNode.children = getJsonTree(data, node.id);
          itemArr.push(newNode);
        }
      }
      return itemArr;
    };


    const handleUpdateMenu = () => {
      roleAssignMenusDTO.value.menuIds = checkedKeys.value
      assignMenus(roleAssignMenusDTO.value).then(response => {
        console.log(response);
        let res = response.data;
        if(res.code==200){
          message.success("配置成功");
          menuModelVisible.value = false;
        }else{
          message.error(res.msg);
        }
      })
    }

    const setSelect = (menuId: any) => {
      checkedKeys.value.push(menuId);
      expandedKeys.value.push(menuId);
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
      roleList,
      loading,
      handleTableChange,
      role,
      modelVisible,
      modalLoading,
      add,
      edit,
      handleDelete,
      handleStatusChange,
      handleSaveOrUpdate,
      menuModelVisible,
      menuModalLoading,
      configMenu,
      treeData,
      expandedKeys,
      checkedKeys,
      handleUpdateMenu
    }
  },
})
</script>
<style>

</style>