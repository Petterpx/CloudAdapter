package com.petterp.cloud.rvadapter.base

import android.annotation.SuppressLint
import com.petterp.cloud.rvadapter.entry.MultiItemEntity

/**
 * @Author petterp
 * @Date 2020/8/2-8:30 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 多itemAdapter
 * [T]  多ItemEntry
 * [VH] Holder
 */
@SuppressLint("UseSparseArrays")
abstract class BaseMultiItemAdapter<T : MultiItemEntity, VH : BaseViewHolder>(data: MutableList<T>? = null) :
    BaseQuickAdapter<T, VH>(0, data) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemLayout
    }
}