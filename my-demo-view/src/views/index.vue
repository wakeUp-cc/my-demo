<template>
  <div id="index">
    <div class="cube-class">
      <i class="el-icon-user-solid"></i>
      <div>当前会员数</div>
      <div>12222</div>
    </div>
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
  props: {},
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
  computed: {},
  watch: {},
  methods: {}
}
</script>

<style scoped>
#index {
  background: #eff1f4;
}

.cube-class {
  height: 15%;
  width: 20%;
  background: aqua;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin-left: 4%;

}

.cube-class > i {
  font-size: 60px;
  position: relative;
  top: 25%;
  left: 6%;
}

.cube-class > div {
  text-align: right;
  padding-right: 8%;
}
</style>
