package com.cloud.adapter.data.source

import androidx.paging.PagingSource
import com.cloud.adapter.api.ApiService
import com.cloud.adapter.api.callHttp

/**
 * @Author petterp
 * @Date 2020/10/28-7:09 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 单item dataSource示例
 */
class PagingWanDataSource(private val apiService: ApiService) : PagingSource<Int, String>() {

    /**
     * params.key 当前页
     * prevKey 上一页
     * nextKey 下一页
     * LoadResult.Pager() 加载成功
     * LoadResult.Error(Throwable) 加载失败，将及时反映到全局监听
     *
     * */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val position = params.key ?: 0
        return callHttp {
            apiService.getList(position)
        }?.run {
            LoadResult.Page(
                data = datas.map {
                    it.title
                },
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (datas.isEmpty()) null else position + 1
            )
        } ?: LoadResult.Error(NullPointerException())

    }
}