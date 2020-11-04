package com.cloud.paging

import androidx.paging.CombinedLoadStates

/**
 * @Author petterp
 * @Date 2020/8/3-6:49 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 监听LoadState加载进度
 */
interface LoadStateListener {

    /** 加载结束 */
    fun notLoading(combinedLoadStates: CombinedLoadStates)

    /** 加载中  */
    fun loading(combinedLoadStates: CombinedLoadStates)

    /** 加载失败 */
    fun error(combinedLoadStates: CombinedLoadStates)


}