<template>
  <div>
    <el-tabs v-model="tableName" type="card" editable @tab-remove="removeTable">
      <el-tab-pane
        :key="item.name"
        v-for="item in editableTabs"
        :label="item.title"
        :name="item.name"
      >
        <router-view/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  components: {},
  props: {},
  created () {
  },
  mounted () {
  },
  data () {
    return {
      tableName: '',
      editableTabs: [{
        title: 'Tab 1',
        name: '1',
        path: ''
      }],
      path: null
    }
  },
  watch: {
    $route (to, from) {
      console.log(to.path)
    }
  },
  methods: {
    removeTable (name) {
      for (let i = 0, len = this.editableTabs.length; i < len; i++) {
        if (this.editableTabs[i].name === name) {
          this.editableTabs.splice(i, 1)
        }
      }
    }
  }
}
</script>
<style scoped>
</style>
