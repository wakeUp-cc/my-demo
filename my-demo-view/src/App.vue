<template>
  <div id="app">
    <!--菜单树-->
    <menuComponents v-if="menuShow" :menuTree="menuTree"></menuComponents>
    <router-view></router-view>
  </div>
</template>

<script>
import menuComponents from '@/components/menu'
import menuApi from '@/api/menu'

export default {
  name: 'App',
  components: {menuComponents},
  data () {
    return {
      // 是否显示菜单,如果要隐藏,则调用this.$parent.menuShow = false
      menuShow: true,
      // 菜单树
      menuTree: null
    }
  },
  mounted () {
  },
  methods: {
    // 加载菜单树,只能在最外层加载,否则会形成死循环
    loadMenuTree () {
      this.$http.get(menuApi.getMenuTree)
        .then((res) => {
          this.menuTree = res.message
          // 菜单树数据存入缓存,方便后期使用
          sessionStorage.setItem('menuTree', JSON.stringify(this.menuTree))
        })
    }
  },
  watch: {
    menuShow (newVal, oldVal) {
      if (newVal) {
        this.loadMenuTree()
      }
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
