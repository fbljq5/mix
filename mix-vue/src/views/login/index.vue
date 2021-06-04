<template>
  <div class="login-box">
    <div class="login-logo">
      <!-- <img src="~@/assets/images/logo.png" style="width: 50%" /> -->
      <h1>MIX 权限管理系统</h1>
    </div>
    <a-form
      layout="horizontal"
      :model="formInline"
      @submit.prevent="handleLogin"
    >
      <a-form-item>
        <a-input
          v-model:value="formInline.username"
          size="large"
          placeholder="请输入账号"
        >
          <template #prefix><user-outlined type="user" /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-model:value="formInline.password"
          size="large"
          type="password"
          placeholder="请输入密码"
          autocomplete="new-password"
        >
          <template #prefix><lock-outlined type="user" /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button
          type="primary"
          html-type="submit"
          size="large"
          :loading="loading"
          block
        >
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script lang="ts">
import { message } from "ant-design-vue";
import { defineComponent, reactive, toRef, toRefs, UnwrapRef } from "vue";
import { login } from "@/api/auth/auth";
import { useRouter } from "vue-router";

interface FormState {
  user: string;
  password: string;
}
export default defineComponent({
  name: "Login",

  setup() {
    const state = reactive({
      loading: false,
      formInline: {
        username: "admin",
        password: "123456",
      },
    });

    const router = useRouter();

    const handleLogin = async () => {
      const { username, password } = state.formInline;
      if (username.trim() == "" || password.trim() == "") {
        return message.warning("用户名或密码不能为空！");
      }
      state.loading = true;
      login(state.formInline).then((response) => {
        state.loading = false;
        const res = response.data;
        if (res.code == 200) {
          localStorage.setItem("MIX_TOKEN", res.data);
          router.push("/");
        }else{
          message.error(res.msg);
        }
      });
    };
    return {
      ...toRefs(state),
      handleLogin,
    };
  },
});
</script>
<style lang="less" scoped>
.login-box {
  width: 100vw;
  height: 100vh;
  display: flex;
  padding-top: 20%;
  flex-direction: column;
  align-items: center;
  background-size: 100% 100%;
  background-color: #d9afd9;
  background-image: linear-gradient(0deg, #d9afd9 0%, #97d9e1 100%);
}
</style>

