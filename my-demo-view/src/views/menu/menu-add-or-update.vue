<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    width="30%"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="菜单名称"></el-input>
      </el-form-item>
      <el-form-item label="菜单地址" prop="path">
        <el-input v-model="dataForm.path" placeholder="菜单地址"></el-input>
      </el-form-item>
      <el-form-item label="菜单图标" prop="icon">
        <el-input v-model="dataForm.icon" placeholder="菜单图标"></el-input>
      </el-form-item>
      <el-form-item label="菜单排序" prop="sort">
        <el-input v-model="dataForm.sort" placeholder="菜单排序"></el-input>
      </el-form-item>
      <el-form-item label="是否显示   0不显示  1显示" prop="showStatus">
        <el-input v-model="dataForm.showStatus" placeholder="是否显示   0不显示  1显示"></el-input>
      </el-form-item>
      <el-form-item label="父菜单ID   0为顶级菜单" prop="parentId">
        <el-input v-model="dataForm.parentId" placeholder="父菜单ID   0为顶级菜单"></el-input>
      </el-form-item>
      <el-form-item label="创建人员ID" prop="createUser">
        <el-input v-model="dataForm.createUser" placeholder="创建人员ID"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
      </el-form-item>
      <el-form-item label="修改人员ID" prop="updateUser">
        <el-input v-model="dataForm.updateUser" placeholder="修改人员ID"></el-input>
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
        name: '',
        path: '',
        icon: '',
        sort: '',
        showStatus: '',
        parentId: '',
        createUser: '',
        createTime: '',
        updateUser: '',
        updateTime: ''
      },
      dataRule: {
        name: [
          {required: true, message: '菜单名称不能为空', trigger: 'blur'}
        ],
        path: [
          {required: true, message: '菜单地址不能为空', trigger: 'blur'}
        ],
        icon: [
          {required: true, message: '菜单图标不能为空', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '菜单排序不能为空', trigger: 'blur'}
        ],
        showStatus: [
          {required: true, message: '是否显示   0不显示  1显示不能为空', trigger: 'blur'}
        ],
        parentId: [
          {required: true, message: '父菜单ID   0为顶级菜单不能为空', trigger: 'blur'}
        ],
        createUser: [
          {required: true, message: '创建人员ID不能为空', trigger: 'blur'}
        ],
        createTime: [
          {required: true, message: '创建时间不能为空', trigger: 'blur'}
        ],
        updateUser: [
          {required: true, message: '修改人员ID不能为空', trigger: 'blur'}
        ],
        updateTime: [
          {required: true, message: '修改时间不能为空', trigger: 'blur'}
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
            url: `/cc/menu/info/${this.dataForm.id}`,
            method: 'get'
          }).then((data) => {
            if (data && data.code === 200) {
              this.dataForm.name = data.message.name
              this.dataForm.path = data.message.path
              this.dataForm.icon = data.message.icon
              this.dataForm.sort = data.message.sort
              this.dataForm.showStatus = data.message.showStatus
              this.dataForm.parentId = data.message.parentId
              this.dataForm.createUser = data.message.createUser
              this.dataForm.createTime = data.message.createTime
              this.dataForm.updateUser = data.message.updateUser
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
            url: `/cc/menu/saveOrUpdateBatch`,
            method: 'post',
            data: [{
              'id': this.dataForm.id || undefined,
              'name': this.dataForm.name,
              'path': this.dataForm.path,
              'icon': this.dataForm.icon,
              'sort': this.dataForm.sort,
              'showStatus': this.dataForm.showStatus,
              'parentId': this.dataForm.parentId,
              'createUser': this.dataForm.createUser,
              'createTime': this.dataForm.createTime,
              'updateUser': this.dataForm.updateUser,
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
