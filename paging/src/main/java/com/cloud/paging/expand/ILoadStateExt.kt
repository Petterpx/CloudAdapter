package com.cloud.paging.expand

import androidx.paging.LoadState

/**
 * @Author petterp
 * @Date 2020/11/4-5:54 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 便于LoadStateaAdapter的Ext扩展
 */
interface ILoadStateExt {
    fun bind(loadState: LoadState)
}