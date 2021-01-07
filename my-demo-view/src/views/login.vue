<template>
  <div>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="操作">
        <el-select v-model="loginOrRegister">
          <el-option v-for="(v,index) in loginOrRegisterSelect" :label="v.label" :value="v.value"
                     :key="index"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账号">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="用户名" v-show="!loginOrRegister">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input show-password v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="repeatPassword" v-show="!loginOrRegister">
        <el-input show-password v-model="form.repeatPassword"></el-input>
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
import userApi from '@/api/user'

export default {

  components: {},
  props: {},
  data () {
    let repeatPassword = (rule, value, callback) => {
      if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        repeatPassword: [{required: true, validator: repeatPassword, trigger: 'change'}]
      },
      form: {
        username: null,
        password: null,
        repeatPassword: null,
        name: null
      },
      loginOrRegister: true,
      loginOrRegisterSelect: [
        {label: '登录', value: true},
        {label: '注册', value: false}
      ]
    }
  },
  mounted () {
  },
  methods: {
    reset () {
      for (let formElement in this.form) {
        this.form[formElement] = null
      }
    },
    login () {
      // 进行MD5加密
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$http.post(userApi.login, this.form)
            .then((res) => {
              if (res) {
                this.$message.success('登录成功!')
              } else {
                this.$message.error('登录失败,请检查账号或密码!')
              }
            })
        }
      })
    },
    register () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.form.password = this.$md5(this.form.password)
          this.$http.post(userApi.register, [this.form])
            .then((res) => {
              if (res) {
                this.$message.success('注册成功!')
              } else {
                this.$message.error('注册失败,请与管理员联系!')
              }
            })
        }
      })
    }
  }
}
</script>
<style scoped>
</style>
