<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    width="30%"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="会员名称"></el-input>
    </el-form-item>
    <el-form-item label="会员年龄" prop="age">
      <el-input v-model="dataForm.age" placeholder="会员年龄"></el-input>
    </el-form-item>
    <el-form-item label="会员性别" prop="sex">
      <el-input v-model="dataForm.sex" placeholder="会员性别"></el-input>
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
        name: '',
        age: '',
        sex: '',
        createTime: '',
        createUser: ''
      },
      dataRule: {
        name: [
          { required: true, message: '会员名称不能为空', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '会员年龄不能为空', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '会员性别  字典表 sex不能为空', trigger: 'blur' }
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
            url: `/cc/member/info/${this.dataForm.id}`,
            method: 'get'
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.dataForm.name = data.message.name
              this.dataForm.age = data.message.age
              this.dataForm.sex = data.message.sex
              this.dataForm.createTime = data.message.createTime
              this.dataForm.createUser = data.message.createUser
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
            url: `/cc/member/saveOrUpdate`,
            method: 'post',
            data: {
              'id': this.dataForm.id || undefined,
              'name': this.dataForm.name,
              'age': this.dataForm.age,
              'sex': this.dataForm.sex,
              'createTime': this.dataForm.createTime,
              'createUser': this.dataForm.createUser
            }
          }).then(({data}) => {
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
