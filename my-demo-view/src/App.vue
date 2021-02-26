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
    // 防止页面刷新导致menuTree重置为null
    if (!this.menuTree) {
      this.menuTree = JSON.parse(sessionStorage.getItem('menuTree'))
    }
    // 默认第一个table高亮
    this.defaultActive = this.menuTree[0].path
  },
  data () {
    return {
      // 是否显示菜单,如果要隐藏,则调用this.$parent.menuShow = false
      menuShow: true,
      // 菜单树
      menuTree: null,
      // 默认高亮的table选项
      defaultActive: null
    }
  },
  methods: {
    // 点击table传递菜单路径,更改选中的菜单的颜色
    handleSelect (key) {
      this.defaultActive = key
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
