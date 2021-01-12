<template>
  <div id="app">
    <!--菜单树-->
    <menuComponents v-show="menuShow"></menuComponents>
    <router-view/>
  </div>
</template>

<script>
import menuComponents from '@/components/menu'

export default {
  name: 'App',
  components: {menuComponents},
  data () {
    return {
      // 是否显示菜单,如果要显示,则调用this.$parent.menuShow = true
      menuShow: false
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

* {
  font-size: 15px;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  box-sizing: border-box;
}
</style>
