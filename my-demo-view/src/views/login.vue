<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="操作">
        <el-select v-model="loginOrRegister">
          <el-option v-for="v in loginOrRegisterSelect" :label="v.lable" :value="v.value" :key="v.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" v-show="!loginOrRegister">
        <el-input v-model="form.repeatPassword"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="reset">重置</el-button>
        <el-button v-if="loginOrRegister" @click="login">登录</el-button>
        <el-button v-else @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  components: {},
  props: {
    form: {
      username: null,
      password: null,
      repeatPassword: null
    },
    loginOrRegister: true,
    loginOrRegisterSelect: [
      {label: '登录', value: true},
      {label: '注册', value: false}
    ]
  },
  data () {
    return {}
  },
  mounted: {},
  methods: {
    reset () {
      for (let formElement in this.form) {
        this.form[formElement] = null
      }
    },
    login () {
      // 进行MD5加密
    },
    register () {

    }
  }
}
</script>
<style scoped>
</style>
