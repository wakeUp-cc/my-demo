<template>
  <div id="app">
    <menuComponents></menuComponents>
    <router-view/>
  </div>
</template>

<script>
import menuComponents from '@/components/menu'
export default {
  name: 'App',
  components: {menuComponents}
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
