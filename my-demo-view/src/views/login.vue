<template>
  <div id="login">
    <el-row style="height: 25%">&nbsp;</el-row>
    <el-row>
      <el-col style="width: 40%">&nbsp;</el-col>
      <el-col style="width: 20%;border-radius: 10px;background: #ffffff">
        <div style="text-align: center;"><h1 style="font-size: 25px;color: #707070">my_demo</h1></div>
        <el-form ref="form" :rules="rules" label-suffix=":" :model="form" label-width="90px"
                 style="width: 90%;margin-top: 25px">
          <el-form-item label="操作">
            <el-select v-model="loginOrRegister">
              <el-option v-for="(v,index) in loginOrRegisterSelect" :label="v.label" :value="v.value"
                         :key="index"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="账号" prop="username">
            <el-input v-model="form.username" @keyup.enter.native="loginOrRegisterMethod"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="name" v-if="!loginOrRegister">
            <el-input v-model="form.name" @keyup.enter.native="loginOrRegisterMethod"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="inputPassword">
            <el-input show-password v-model="form.inputPassword" @keyup.enter.native="loginOrRegisterMethod"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="repeatPassword" v-if="!loginOrRegister">
            <el-input show-password v-model="form.repeatPassword"
                      @keyup.enter.native="loginOrRegisterMethod"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="identifyCode">
            <el-input v-model="form.identifyCode" style="width: 55%"
                      @keyup.enter.native="loginOrRegisterMethod"></el-input>
            <identifyCode @getIdentifyCode="getIdentifyCode" :contentHeight="40" style="float: right"></identifyCode>
          </el-form-item>
          <el-form-item style="text-align: right">
            <el-button @click="reset">重置</el-button>
            <el-button type="primary" @click="loginOrRegisterMethod">{{ loginOrRegister ? '登录' : '注册' }}</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col style="width: 40%">&nbsp;</el-col>
    </el-row>
  </div>
</template>
<script>
import userApi from '@/api/user'
import menuApi from '@/api/menu'
import identifyCode from '@/components/identifyCode'

export default {
  name: 'login',
  components: {identifyCode},
  props: {},
  data () {
    let repeatPassword = (rule, value, callback) => {
      if (value !== this.form.inputPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    let identifyCode = (rule, value, callback) => {
      // 忽略大小写
      if (String(value).toUpperCase() !== String(this.identifyCode).toUpperCase()) {
        callback(new Error('验证码错误!'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        username: [{required: true, message: '账号不能为空!', trigger: 'change'}],
        name: [{required: true, message: '用户名不能为空!', trigger: 'change'}],
        inputPassword: [{required: true, message: '密码不能为空!', trigger: 'change'}],
        repeatPassword: [{required: true, validator: repeatPassword, trigger: 'change'}],
        identifyCode: [{required: true, validator: identifyCode, trigger: 'blur'}]
      },
      form: {
        username: null,
        password: null,
        inputPassword: null,
        repeatPassword: null,
        name: null
      },
      loginOrRegister: true,
      loginOrRegisterSelect: [
        {label: '登录', value: true},
        {label: '注册', value: false}
      ],
      identifyCode: null
    }
  },
  mounted () {
    this.$parent.menuShow = false
  },
  methods: {
    reset () {
      for (let formElement in this.form) {
        this.form[formElement] = null
      }
    },
    loginOrRegisterMethod () {
      if (this.loginOrRegister) {
        this.$refs.form.validate((valid) => {
          if (valid) {
            // 加密
            this.form.password = this.$md5(this.form.inputPassword)
            // 登录
            this.$http.post(userApi.login, this.form)
              .then(loginRes => {
                if (loginRes.message) {
                  this.$message.success('登录成功!')
                  sessionStorage.setItem('token', loginRes.message)
                  // 请求菜单树
                  this.$http.get(menuApi.getMenuTree)
                    .then(menuTreeRes => {
                      this.$parent.menuTree = menuTreeRes.message
                      // 菜单树数据存入缓存,方便后期使用
                      sessionStorage.setItem('menuTree', JSON.stringify(menuTreeRes.message))
                      let menuArray = []
                      // 菜单集合数据存入缓存,方便后期使用
                      this.getMenuArray(menuTreeRes.message, menuArray)
                      sessionStorage.setItem('menuArray', JSON.stringify(menuArray))
                      // 显示菜单树并且跳转至table页
                      this.$parent.menuShow = true
                      // 初始化高亮的table
                      this.$parent.loadMenuTree()
                      this.$router.push('/')
                    })
                } else {
                  this.$message.error('登录失败,请检查账号或密码!')
                  this.reset()
                  this.$nextTick(() => {
                    this.$refs.form.clearValidate()
                  })
                }
              })
          }
        })
      } else {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.form.password = this.$md5(this.form.inputPassword)
            this.$http.post(userApi.register, [this.form])
              .then((res) => {
                if (res.message) {
                  this.$message.success('注册成功!')
                  this.loginOrRegister = true
                } else {
                  this.$message.error('注册失败,请与管理员联系!')
                }
                this.reset()
                this.$nextTick(() => {
                  this.$refs.form.clearValidate()
                })
              })
          }
        })
      }
    },
    // 获取验证码
    getIdentifyCode (identifyCode) {
      this.identifyCode = identifyCode
    },
    // 获取菜单集合
    getMenuArray (menuTree, menuArray) {
      for (let menuTreeElement of menuTree) {
        menuArray.push(menuTreeElement)
        if (menuTreeElement.children) {
          this.getMenuArray(menuTreeElement.children, menuArray)
        }
      }
    }
  }
}
</script>

<style scoped>
#login {
  background: url("../../static/images/background.jpg");
  background-size: 100% 100%;
  -moz-background-size: 100% 100%;
  margin: 0;
  width: 100%;
}
</style>
