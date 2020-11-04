package com.cloud.adapter.api

import com.cloud.adapter.data.WanListBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author petterp
 * @Date 2020/8/5-8:29 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 一个api,wanAndroid
 */
interface ApiService {
    @GET("/article/listproject/{page}/json")
    @JvmSuppressWildcards
    suspend fun  getList(@Path("page") page: Int): PeopleResponse<WanListBean>
}