<template>
  <div class="login-box">
    <div class="login-logo">
      <svg-icon icon-class="logo" />
      <h1>MIX Admin</h1>
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
          placeholder="admin"
        >
          <template #prefix><user-outlined type="user" /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-model:value="formInline.password"
          size="large"
          type="password"
          placeholder="123456"
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
import axios from "axios";
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
        username: "",
        password: "",
      },
    });
    const handleLogin = async () => {
      const { username, password } = state.formInline;
      if (username.trim() == "" || password.trim() == "") {
        return message.warning("用户名或密码不能为空！");
      }
      state.loading = true;
      axios.get("http://localhost:8502/mix-auth/auth/login").then((response) => {
        console.log(response);
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
  background: url("~@/assets/images/login-bg.jpg");
  background-size: 100%;

  img {
    height: 48px;
  }

  h1 {
    color: white;
    margin-left: 10px;
  }
}
</style>

