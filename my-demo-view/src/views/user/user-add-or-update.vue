<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    width="30%"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="name">
      <el-input v-model="dataForm.name" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0,
        username: '',
        password: '',
        name: '',
        createTime: '',
        updateTime: ''
      },
      dataRule: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '注册时间不能为空', trigger: 'blur' }
        ],
        updateTime: [
          { required: true, message: '修改时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http.request({
            url: `/cc/user/info/${this.dataForm.id}`,
            method: 'get'
          }).then((data) => {
            if (data && data.code === 200) {
              this.dataForm.username = data.message.username
              this.dataForm.password = data.message.password
              this.dataForm.name = data.message.name
              this.dataForm.createTime = data.message.createTime
              this.dataForm.updateTime = data.message.updateTime
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http.request({
            url: `/cc/user/saveOrUpdateBatch`,
            method: 'post',
            data: [{
              'id': this.dataForm.id || undefined,
              'username': this.dataForm.username,
              'password': this.dataForm.password,
              'name': this.dataForm.name,
              'createTime': this.dataForm.createTime,
              'updateTime': this.dataForm.updateTime
            }]
          }).then((data) => {
            if (data && data.message && data.code === 200) {
              this.$message.success('操作成功!')
              this.visible = false
              this.$emit('refreshDataList')
            } else {
              this.$message.error('操作失败!')
            }
          })
        }
      })
    }
  }
}
</script>
