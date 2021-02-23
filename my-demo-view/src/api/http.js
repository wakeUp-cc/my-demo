import axios from 'axios'
// 请求等待响应框
import {Loading, Message} from 'element-ui'
import router from '@/router'

// 加载全局的loading
let loadingInstance = null

// 请求拦截
axios.interceptors.request.use(config => {
  // 1. 这个位置就请求前最后的配置
  // 发起请求时加载全局loading，请求失败或有响应时会关闭
  loadingInstance = Loading.service({
    spinner: 'fa fa-spinner fa-spin fa-3x fa-fw',
    text: '拼命加载中...'
  })
  // 获取vue缓存中存放的token给后端
  let token = sessionStorage.getItem('token')
  // 添加时间戳参数，防止浏览器（IE）对get请求的缓存
  if (config.method === 'get') {
    config.params = {
      ...config.params,
      t: new Date().getTime(),
      token: token
    }
  } else {
    config.params = {
      ...config.params,
      token: token
    }
  }
  // 请求超时,具体根据需求来
  config.timeout = 10000
  // 跨域,如果不在同一个服务器,那么后台也要设置跨域请求
  config.withCredentials = true
  // 请求前缀
  // config.baseURL = window.location.origin
  config.baseURL = 'http://localhost:8001'
  return config
}, error => {
  return Promise.reject(error)
})

// 响应拦截
axios.interceptors.response.use(response => {
  // 请求成功
  // 关闭加载框
  loadingInstance.close()
  // 1. 根据自己项目需求定制自己的拦截
  // 如果请求接口返回失败,那么就进行弹窗提示
  // 由于axios的返回结果被封装了一层,需要取data值,才是后台返回的数据
  if (response.data.code === 500) {
    Message.error(response.data.message)
    // 未登录
  } else if (response.data.code === 1000) {
    Message.error(response.data.message)
    router.push({
      path: `/login`
    })
  }
  // 2. 然后返回数据
  return response.data
}, error => {
  // 关闭加载框
  loadingInstance.close()
  // 请求失败
  Message({
    message: error,
    type: 'error'
  })
  return Promise.reject(new Error(error))
})

export default {
  /**
   * 普通请求
   * @param config
   * @returns {*}
   */
  request: (config) => {
    return axios(config)
  },
  /**
   * get 请求
   * @param url 接口路由
   * @param data 参数
   * @returns {AxiosPromise<any>}
   */
  get: (url, data) => {
    return axios.get(url, {params: data})
  },

  /**
   * post 请求
   *
   * @param url 接口路由
   * @param data 接口参数
   * @returns {AxiosPromise<any>}
   */
  post (url, data) {
    return axios.post(url, data)
  },

  /**
   * put请求
   * @param url 接口路由
   * @param data 接口参数
   * @param auth 是否需要带登录信息
   * @returns {AxiosPromise<any>}
   */
  put (url, data) {
    return axios.put(url, data)
  },

  /**
   * 删除
   * @param url 接口路由
   * @param auth 是否需要带登录信息
   * @returns {AxiosPromise}
   */
  del (url) {
    return axios.delete(url)
  },

  /**
   * 上传文件
   * @param url 接口路由
   * @param file 接口文件
   * @param auth 是否需要带登录信息
   */
  uploader (url, file) {
    let param = new FormData()
    param.append('file', file)
    return axios.post(url, param)
  }
}
