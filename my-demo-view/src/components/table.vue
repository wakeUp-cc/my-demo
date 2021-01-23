<template>
  <div>
    <el-tabs v-model="selectedTab" type="card" editable @tab-remove="removeTable" :before-leave="beforeLeave">
      <el-tab-pane
        v-for="tab in tabs"
        :key="tab.name"
        :label="tab.title"
        :name="tab.name"
      >
      </el-tab-pane>
    </el-tabs>
    <!--缓存的页面名称,要求每个vue都必须有name,并且和访问路径名相同,且不带字符/-->
    <keep-alive :include="cachePath.join(',')">
      <router-view/>
    </keep-alive>
  </div>
</template>
<script>
import menuApi from '@/api/menu'

export default {
  name: 'tableComponents',
  components: {},
  props: {},
  created () {
  },
  mounted () {
    this.detectTabs()
  },
  data () {
    return {
      // 选中的table
      selectedTab: '/index',
      // 所有的table
      tabs: [],
      // 缓存的页面名称
      cachePath: []
    }
  },
  watch: {
    // 监听路由动向
    $route (to, from) {
      this.selectedTab = to.path
      if (this.tabs.filter(editableTab => editableTab.path === to.path).length === 0) {
        // 查询菜单名称
        this.$http.get(menuApi.list, {path: to.path})
          .then((res) => {
            if (res.code === 200) {
              this.tabs.push({title: res.message.records[0].name, name: to.path, path: to.path})
              // 当页面关闭时,清除掉页面的缓存,要求页面的名称和路径名称相同,且不包含字符/
              this.cachePath.push(to.path.replace('/', ''))
            }
          })
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
          // 关闭table后,自动访问上一个table
          if (this.$route.path === name) {
            this.selectedTab = this.tabs[i - 1].path
            this.$router.push(this.selectedTab)
          }
        }
      }
    },
    // 点击table时,跳转至对应路由
    beforeLeave (activeName, oldActiveName) {
      // 防止重复路由跳转
      if (this.$route.path !== activeName) {
        this.$router.push(activeName)
      }
    },
    // 初始化table
    detectTabs () {
      this.tabs = [{title: '首页', name: '/index', path: '/index'}]
      this.cachePath = ['index']
      if (this.$route.path !== this.tabs[0].path) {
        this.$router.push(this.tabs[0].path)
      }
    }
  }
}
</script>
<style scoped>
</style>
