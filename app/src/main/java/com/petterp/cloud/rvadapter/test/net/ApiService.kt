package com.petterp.cloud.rvadapter.test.net

import com.petterp.cloud.rvadapter.test.net.WanListBean
import com.petterp.cloud.rvadapter.test.net.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author petterp
 * @Date 2020/8/5-8:29 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
interface ApiService {
    @GET("/article/listproject/{page}/json")
    @JvmSuppressWildcards
    suspend fun  getMainList(@Path("page") page: Int): PeopleResponse<WanListBean>
}