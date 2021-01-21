<template>
  <div id="app">
    <!--菜单树-->
    <menuComponents v-if="menuShow" :menuTree="menuTree"></menuComponents>
    <tableComponents v-if="menuShow" :checkMenuPath="checkMenuPath"></tableComponents>
    <router-view v-if="!menuShow"></router-view>
  </div>
</template>

<script>
import menuComponents from '@/components/menu'
import tableComponents from '@/components/table'
import menuApi from '@/api/menu'

export default {
  name: 'App',
  components: {menuComponents, tableComponents},
  data () {
    return {
      // 是否显示菜单,如果要隐藏,则调用this.$parent.menuShow = false
      menuShow: true,
      // 菜单树
      menuTree: null,
      checkMenuPath: null
    }
  },
  mounted () {
    this.loadMenuTree()
  },
  methods: {
    // 加载菜单树
    loadMenuTree () {
      this.$http.get(menuApi.getMenuTree)
        .then((res) => {
          this.menuTree = res.message
        })
    }
  }
}
</script>

<style>
#app, html, body {
  height: 100%;
  padding: 0;
  margin: 0;
}

#app > div {
  height: 100%;
  width: 89%;
  padding: 0;
  margin: 0;
  float: left;
}

* {
  font-size: 15px;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  box-sizing: border-box;
}
</style>
