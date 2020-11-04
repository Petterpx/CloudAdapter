package com.cloud.paging.config

/**
 * @Author petterp
 * @Date 2020/10/29-7:29 PM
 * @Email ShiyihuiCloud@163.com
 * @Function Page的一些配置
 */

/** PagingConfig配置类 */
class RvPagingConfig private constructor(
    internal val pageSize: Int,
    internal val prefetchDistance: Int,
    internal val initialLoadSize: Int,
    internal val maxSize: Int,
    internal val enablePlaceholders: Boolean
) {
    private constructor(builder: Builder) : this(
        builder.pageSize,
        builder.prefetchDistance,
        builder.initialLoadSize,
        builder.maxSize,
        builder.enablePlaceholders
    )

    companion object {
        internal const val dfPageSize = 10
        internal const val dfPrefetchDistance = 10
        internal const val dfInitLoadSize = 40
        internal const val defaultMaxSize = Int.MAX_VALUE
        internal const val dfEnablePlaceholders = false
        internal var config: RvPagingConfig? = null
        fun build(init: Builder.() -> Unit) {
            config = Builder(init).build()
        }
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            this.init()
        }

        var pageSize = dfPageSize
        var prefetchDistance = dfPrefetchDistance
        var initialLoadSize = dfInitLoadSize
        var maxSize = defaultMaxSize
        var enablePlaceholders = dfEnablePlaceholders

        fun pageSize(init: Builder.() -> Int) = apply { pageSize = init() }
        fun prefetchDistance(init: Builder.() -> Int) = apply { prefetchDistance = init() }
        fun initialLoadSize(init: Builder.() -> Int) = apply { initialLoadSize = init() }
        fun maxSize(init: Builder.() -> Int) = apply { maxSize = init() }
        fun enablePlaceholders(init: Builder.() -> Boolean) = apply { enablePlaceholders = init() }

        fun build() = RvPagingConfig(this)
    }
}