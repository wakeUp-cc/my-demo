<template>
  <div :id="echartsId" :style="'width: '+width+'px;'+'height: '+height+'px;'"></div>
</template>

<script>
import uuid from 'uuid/v1'

export default {
  name: 'echarts',
  created () {
    this.echartsId = uuid()
  },
  mounted () {
    this.echartsInit()
  },
  props: {
    // 指定图表的配置项和数据
    option: {
      type: Object,
      // eslint-disable-next-line vue/require-valid-default-prop
      default: {}
    },
    width: {
      type: String,
      default: '600'
    },
    height: {
      type: String,
      default: '400'
    }
  },
  data () {
    return {
      // 防止一个页面引入两次组件,导致ID重复,采用UUID
      echartsId: null
    }
  },
  methods: {
    echartsInit () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById(this.echartsId))
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(this.option)
    }
  }
}
</script>

<style scoped>
</style>
