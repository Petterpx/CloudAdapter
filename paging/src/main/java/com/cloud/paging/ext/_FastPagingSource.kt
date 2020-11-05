package com.cloud.paging.ext

import androidx.paging.*
import com.cloud.paging.config.RvPagingConfig

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
