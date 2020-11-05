package com.cloud.paging.ext

import androidx.paging.PagingSource

/**
 * @Author petterp
 * @Date 2020/11/3-7:03 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 按页加载的pagingSource
 */

/** 网络判断密封类 */
sealed class StatusRvData {
    data class Success<Bean : Any>(val data: List<Bean>) : StatusRvData()
    data class Error(val throwable: Throwable) : StatusRvData()
}

/**
 * [defaultIndex] 默认页数
 * [obj] 触发网络回调
 * */
inline fun <reified Bean : Any> createIndexPagingSource(
    defaultIndex: Int = 0,
    noinline obj: suspend (Int) -> StatusRvData,
): PagingSource<Int, Bean> {
    return IndexPagingSource<Bean>(obj, defaultIndex)
}

/** 按页加载的PagingSource */
class IndexPagingSource<Bean : Any>(
    private val obj: suspend (Int) -> StatusRvData,
    private val defaultIndex: Int
) :
    PagingSource<Int, Bean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Bean> {
        val key = params.key
        val position = key ?: defaultIndex
        val data = obj(position)
        return if (data is StatusRvData.Success<*>) {
            LoadResult.Page(
                data = data.data as List<Bean>,
                prevKey = if (position == defaultIndex) null else position - 1,
                nextKey = if (data.data.isEmpty()) null else position + 1
            )
        } else {
            LoadResult.Error((data as StatusRvData.Error).throwable)
        }
    }

}