package com.petterp.cloud.rvadapter.test.net

import com.cloudx.core.LiveHttp

/**
 * @Author petterp
 * @Date 2020/8/5-8:41 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
object ApiServiceImpl {
    val apiService = LiveHttp.createApi(ApiService::class.java)
}