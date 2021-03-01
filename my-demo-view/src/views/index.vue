<template>
  <div>
    首页
    <echarts :option="option"></echarts>
  </div>
</template>

<script>
import echarts from '@/components/echarts'

export default {
  name: 'index',
  components: {
    echarts
  },
  created () {
  },
  mounted () {
  },
  props: {
  },
  data () {
    return {
      option: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [150, 230, 224, 218, 135, 147, 260],
          type: 'line'
        }]
      }
    }
  },
  computed: {
  },
  watch: {
  },
  methods: {
  }
}
</script>

<style scoped>
</style>
