<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="主键">
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="账号">
      </el-table-column>
      <el-table-column
        prop="password"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="密码">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="注册时间">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="修改时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="current"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="size"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './user-add-or-update'

export default {
  name: 'user',
  data () {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      current: 1,
      size: 10,
      total: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  created () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http.request({
        url: '/cc/user/list',
        method: 'get',
        params: {
          'isPage': true,
          'current': this.current,
          'size': this.size
        }
      }).then((data) => {
        if (data && data.code === 200) {
          this.dataList = data.message.records
          this.total = data.message.total
        } else {
          this.dataList = []
          this.total = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.size = val
      this.current = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.current = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id) {
      let ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.request({
          url: '/cc/user/delete',
          method: 'post',
          data: ids
        }).then((data) => {
          if (data && data.message && data.code === 200) {
            this.$message.success('操作成功!')
            this.getDataList()
          } else {
            this.$message.error('操作失败!')
          }
        })
      })
    }
  }
}
</script>
