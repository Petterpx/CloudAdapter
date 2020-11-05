package com.cloud.paging.base

import android.view.View
import androidx.paging.LoadState
import com.cloud.adapter.base.BaseViewHolder

/**
 * @Author petterp
 * @Date 2020/11/5-3:27 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 通用的loadHolder
 */
open class BaseLoadViewHolder(view: View) : BaseViewHolder(view) {
    fun bind(loadState: LoadState) {}
}