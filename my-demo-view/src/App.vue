<template>
  <div id="app">
    <!--菜单树-->
    <menuComponents v-if="menuShow" :menuTree="menuTree" :defaultActive="defaultActive" @handleSelect="handleSelect"></menuComponents>
    <router-view></router-view>
  </div>
</template>

<script>
import menuComponents from '@/components/menu'

export default {
  name: 'App',
  components: {menuComponents},
  mounted () {
    this.loadMenuTree()
  },
  data () {
    return {
      // 是否显示菜单,如果要隐藏,则调用this.$parent.menuShow = false
      menuShow: true,
      // 菜单树
      menuTree: null,
      // 默认高亮的table选项
      defaultActive: null,
      // 面包屑
      breadCrumbs: []
    }
  },
  methods: {
    // 点击table传递菜单路径,更改选中的菜单的颜色
    handleSelect (key) {
      this.defaultActive = key
      let menuArray = JSON.parse(sessionStorage.getItem('menuArray'))
      let menu = menuArray.filter(menu => menu.path === key)[0]
      // 只保留第一个面包屑,其他的面包屑清空
      this.breadCrumbs.splice(1, this.breadCrumbs.length - 1)
      if (menu.parentId !== 0) {
        let parentMenuArray = []
        parentMenuArray.push({name: menu.name, path: menu.path})
        this.getParentBreadCrumbs(menu.parentId, menuArray, parentMenuArray)
        // 获取所有父面包屑
        this.breadCrumbs = this.breadCrumbs.concat(parentMenuArray)
        // 如果不是第一个菜单,那么就直接往后拼接面包屑
      } else if (menu.id !== menuArray[0].id) {
        this.breadCrumbs.push({name: menu.name, path: menu.path})
      }
    },
    // 获取点击的面包屑以及所有的父面包屑
    getParentBreadCrumbs (parentId, menuArray, parentMenuArray) {
      for (let menuArrayElement of menuArray) {
        if (menuArrayElement.id === parentId) {
          parentMenuArray.unshift({name: menuArrayElement.name, path: menuArrayElement.path})
          if (menuArrayElement.parentId !== 0) {
            this.getParentBreadCrumbs(menuArrayElement.parentId, menuArray, parentMenuArray)
          }
        }
      }
    },
    // 加载menuTree以及初始化高亮的table
    loadMenuTree () {
      // 防止页面刷新导致menuTree重置为null
      if (!this.menuTree) {
        this.menuTree = JSON.parse(sessionStorage.getItem('menuTree'))
      }
      if (this.menuTree) {
        // 默认第一个table高亮
        this.defaultActive = this.menuTree[0].path
        this.loadBreadCrumbs()
      }
    },
    // 初始化面包屑
    loadBreadCrumbs () {
      // 面包屑的第一级永远是菜单的第一个
      this.breadCrumbs = [{name: this.menuTree[0].name, path: this.menuTree[0].path}]
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
