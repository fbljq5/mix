<template>
  <a-layout-content style="margin: 0 16px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>权限管理</a-breadcrumb-item>
      <a-breadcrumb-item>用户管理</a-breadcrumb-item>
    </a-breadcrumb>

    <p>
      <a-form layout="inline" :model="param" ref="formRef">
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="查询用户名">
          </a-input>
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
        :data-source="userList"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
    >
      <template v-slot:action="{ record }">
        <a-space size="small">
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

  <a-modal v-model:visible="modelVisible" title="用户表单" @ok="handleSaveOrUpdate"
           :confirm-loading="modalLoading">
    <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="用户名">
        <a-input v-model:value="user.userName"></a-input>
      </a-form-item>

      <a-form-item label="邮箱地址">
        <a-input v-model:value="user.email"></a-input>
      </a-form-item>

      <a-form-item label="手机号码">
        <a-input v-model:value="user.phone"></a-input>
      </a-form-item>

      <a-form-item label="备注">
        <a-input v-model:value="user.remark"></a-input>
      </a-form-item>

      <a-form-item label="密码">
        <a-input v-model:value="user.password" type="password"></a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts">
import {defineComponent, ref, onMounted} from "vue";
import {message} from "ant-design-vue";
import {getUserList, deleteUser, saveUser, updateUser} from "@/api/admin/user";
import {Tool} from '@/utils/tool';

const columns = [
  {
    title: "用户名",
    dataIndex: "userName",
    sorter: true,
    width: "20%",
    slots: {
      customRender: "userName",
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
  {
    title: "操作",
    key: "action",
    slots: {
      customRender: "action",
    },
  },
];

export default defineComponent({
  setup() {
    const formRef = ref();
    const param = ref();
    param.value = {};
    const userList = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0,
    });


    const loading = ref(false);

    const resetForm = () => {
      param.value.name = "";
      handleQuery({
        page: 1,
        pageSize: pagination.value.pageSize,
      });
    };

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
        username: param.value.name,
      });
    };

    /**
     * 删除
     */
    const handleDelete = (id: number) => {
      deleteUser(id).then((response) => {
        console.log(response);
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
      });
    };

    const user = ref()
    const modelVisible = ref(false);
    const modalLoading = ref(false);
    // 编辑
    const edit = (record: any) => {
      modelVisible.value = true;
      user.value = Tool.copy(record);
    };
    const add = () => {
      modelVisible.value = true;
      user.value = {};
    };

    const handleSaveOrUpdate = () => {
      modalLoading.value = true;
      let promise;
      if (Tool.isNotEmpty(user.value.id)) {
        promise = updateUser(user.value)
      } else {
        promise = saveUser(user.value)
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
      }).catch(err =>{
        modalLoading.value = false;
      })
    }

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
      handleDelete,
      resetForm,
      modelVisible,
      modalLoading,
      user,
      edit,
      add,
      handleSaveOrUpdate,
    };
  },
});
</script>
<style>
</style>