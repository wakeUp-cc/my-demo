<template>
  <el-menu
    style="height: 100%;width: 11%;float: left"
    router
    class="el-menu-vertical-demo"
    :default-active="defaultActive"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <!--循环树菜单-->
    <div v-for="(menu,index) in menuTree" :key="index">
      <!--如果树菜单有子节点,就显示可以展开的样式,index就是路由地址,通过router属性,开启点击后跳转路由-->
      <el-submenu :index="menu.path" v-if="menu.children">
        <template slot="title">
          <i :class="menu.icon"></i>
          <span>{{ menu.name }}</span>
        </template>
        <!--此处自己引用自己,相当于递归调用,将父组件的数据通过组件数据传,传递下去,以此为递归-->
        <menuComponents :menuTree="menu.children" :defaultActive="defaultActive" @handleSelect="handleSelect"></menuComponents>
      </el-submenu>
      <!--没有子节点,显示为没有子节点的样式,index就是路由地址,通过router属性,开启点击后跳转路由-->
      <el-menu-item :index="menu.path" v-else>
        <i :class="menu.icon"></i>
        <span slot="title">{{ menu.name }}</span>
      </el-menu-item>
    </div>
  </el-menu>
</template>

<script>
// 自己引用自己,递归调用
import menuComponents from './menu'

export default {
  name: 'menuComponents',
  props: {
    menuTree: {
      type: Array,
      default: null
    },
    defaultActive: {
      type: String,
      default: null
    }
  },
  mounted () {
  },
  data () {
    return {
    }
  },
  components: {menuComponents},
  // 最好不要做任何操作,只通过传递过来的菜单数值得到菜单,因为这是一个递归菜单树,任何一个操作都会放大N倍
  methods: {
    // 点击table传递菜单路径,更改选中的菜单的颜色
    handleSelect (key, keyPath) {
      this.$emit('handleSelect', key)
    }
  }
}
</script>

<style scoped>
</style>
