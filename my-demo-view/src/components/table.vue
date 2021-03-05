<template>
  <div>
    <!--面包屑-->
    <el-row style="margin-top: 5px">
      <el-col :span="22">
        <el-breadcrumb separator="/" style="position: relative;top: 13px">
          <el-breadcrumb-item :to="{ path: data.path }" v-for="data in $parent.breadCrumbs" :key="data.path">
            {{ data.name }}
          </el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
      <!--个人头像-->
      <el-col :span="2" style="text-align: right">
        <el-dropdown @command="handleCommand" style="margin-right: 10px">
          <el-avatar :src="currentUser.icon">{{ currentUser.name }}</el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="1">个人中心</el-dropdown-item>
            <el-dropdown-item command="2">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
    <!--table项-->
    <el-tabs v-model="selectedTab" type="card" closable @tab-remove="removeTable" :before-leave="beforeLeave">
      <el-tab-pane
        v-for="tab in tabs"
        :key="tab.name"
        :label="tab.title"
        :name="tab.name"
      >
      </el-tab-pane>
    </el-tabs>
    <!--缓存的页面名称,要求每个页面级别vue都必须有name,并且和访问路径名相同,且不带字符/-->
    <keep-alive :include="cachePath.join(',')">
      <router-view/>
    </keep-alive>
  </div>
</template>

<script>
import userApi from '@/api/user'

export default {
  name: 'tableComponents',
  components: {},
  props: {},
  created () {
  },
  mounted () {
    this.detectTabs()
    this.$util.getCurrentUser()
      .then(res => {
        this.currentUser = res.message
      })
  },
  data () {
    return {
      // 选中的table
      selectedTab: '/index',
      // 所有的table
      tabs: [],
      // 缓存的页面名称
      cachePath: [],
      // 当前登录用户
      currentUser: {}
    }
  },
  watch: {
    // 监听路由动向
    $route (to, from) {
      this.selectedTab = to.path
      if (this.tabs.filter(editableTab => editableTab.path === to.path).length === 0) {
        // 查询菜单名称
        let menuArray = JSON.parse(sessionStorage.getItem('menuArray'))
        let menu = menuArray.filter(menu => menu.path === to.path)[0]
        this.tabs.push({title: menu.name, name: to.path, path: to.path})
        // 当页面关闭时,清除掉页面的缓存,要求页面的名称和路径名称相同,且不包含字符/
        this.cachePath.push(to.path.replace('/', ''))
      }
    }
  },
  methods: {
    // 关闭table
    removeTable (name) {
      // 如果table只剩最后一个,则初始化table
      if (this.tabs.length === 1) {
        this.detectTabs()
        return
      }
      for (let i = this.tabs.length - 1; i >= 0; i--) {
        if (this.tabs[i].name === name) {
          this.tabs.splice(i, 1)
          this.cachePath.splice(i, 1)
          // 关闭打开的table时的操作
          if (this.$route.path === name) {
            // 默认访问下一个table
            let jumpTab = this.tabs[i]
            // 如果关闭的是一个最后一个table,那么就访问上一个table
            if (!this.tabs[i]) {
              jumpTab = this.tabs[i - 1]
            }
            this.selectedTab = jumpTab.path
            this.$router.push(this.selectedTab)
          }
        }
      }
    },
    // 点击table时,跳转至对应路由
    beforeLeave (activeName, oldActiveName) {
      // 更改高亮的菜单栏
      this.$parent.handleSelect(activeName)
      // 防止重复路由跳转
      if (this.$route.path !== activeName) {
        this.$router.push(activeName)
      }
    },
    // 初始化table
    detectTabs () {
      let menuTree = JSON.parse(sessionStorage.getItem('menuTree'))[0]
      this.tabs = [{title: menuTree.name, name: menuTree.path, path: menuTree.path}]
      this.cachePath = [menuTree.path.replace('/', '')]
      if (this.$route.path !== this.tabs[0].path) {
        this.$router.push(this.tabs[0].path)
      }
      this.$parent.loadBreadCrumbs()
    },
    // 头像框操作
    handleCommand (command) {
      switch (command) {
        case '1':
          break
        case '2':
          this.logout()
          break
      }
    },
    // 登出
    logout () {
      this.$http.get(userApi.logout)
        .then(() => {
          sessionStorage.removeItem('token')
          this.$router.push('/login')
        })
    }
  }
}
</script>

<style scoped>
</style>
