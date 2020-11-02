package com.petterp.paging.source.ktx

import androidx.paging.*
import com.petterp.paging.source.config.RvPagingConfig

/**
 * @Author petterp
 * @Date 2020/10/30-3:18 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 适用通用的Paging3创建
 */


/** 快捷创建pager
 * [pagingSize] 分页大小，就是你每页加载多少数据
 * [prefetchDistance] 什么位置时去加载,就是说你距离底部还有多远时去加载新数据
 * [initialLoadSize] 首次加载的item长度，推荐为 pageSize * 3
 * [maxSize] 缓存的item长度，默认全部保存
 * [enablePlaceholders] 是否启用占位符，用于数据库item占位
 * [initialKey] 初始秘钥，一般用不到
 * [remoteMediator] 数据库控制器，用于控制什么时候加载数据库数据，便于网络+本地db配合时使用
 * [pagingSourceFactory] 用于定义数据源及如何从该数据源获取数据
 * */
fun <Key : Any, Value : Any> createPager(
    pagingSize: Int? = null,
    prefetchDistance: Int? = null,
    initialLoadSize: Int? = null,
    maxSize: Int? = null,
    enablePlaceholders: Boolean? = null,
    initialKey: Key? = null,
    @OptIn(ExperimentalPagingApi::class)
    remoteMediator: RemoteMediator<Key, Value>? = null,
    pagingSourceFactory: () -> PagingSource<Key, Value>
): Pager<Key, Value> {
    RvPagingConfig.apply {
        return Pager(
            config = PagingConfig(
                pageSize = pagingSize ?: config?.pageSize ?: dfPageSize,
                prefetchDistance = prefetchDistance ?: config?.prefetchDistance
                ?: dfPrefetchDistance,
                initialLoadSize = initialLoadSize ?: config?.initialLoadSize ?: dfInitLoadSize,
                maxSize = maxSize ?: config?.maxSize ?: defaultMaxSize,
                enablePlaceholders = enablePlaceholders ?: config?.enablePlaceholders
                ?: dfEnablePlaceholders,
            ),
            remoteMediator = remoteMediator,
            initialKey = initialKey,
            pagingSourceFactory = pagingSourceFactory
        )
    }
}

/**
 * [defaultIndex] 默认页数
 * [obj] 触发网络回调
 * */
inline fun <reified Bean : Any> createIndexPagingSource(
    defaultIndex: Int = 0,
    noinline obj: suspend (Int) -> CloudNetData,
): PagingSource<Int, Bean> {
    return IndexPagingSource<Bean>(obj, defaultIndex)
}

/** 网络判断密封类 */
sealed class CloudNetData {
    data class Success<Bean : Any>(val data: List<Bean>) : CloudNetData()
    data class Error(val throwable: Throwable) : CloudNetData()
}

/** 按页加载的PagingSource */
class IndexPagingSource<Bean : Any>(
    private val obj: suspend (Int) -> CloudNetData,
    private val defaultIndex: Int
) :
    PagingSource<Int, Bean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Bean> {
        val key = params.key
        val position = key ?: defaultIndex
        val data = obj(position)
        return if (data is CloudNetData.Success<*>) {
            LoadResult.Page(
                data = data.data as List<Bean>,
                prevKey = if (position == defaultIndex) null else position - 1,
                nextKey = if (data.data.isEmpty()) null else position + 1
            )
        } else {
            LoadResult.Error((data as CloudNetData.Error).throwable)
        }
    }

}