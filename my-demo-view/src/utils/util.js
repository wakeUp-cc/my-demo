import http from '@/utils/http'
import userApi from '@/api/user'

const util = {
  /**
   * 获取当前登录用户
   */
  async getCurrentUser () {
    // eslint-disable-next-line no-return-await
    return await http.get(userApi.getCurrentUser + sessionStorage.getItem('token'))
  }
}

export default util
