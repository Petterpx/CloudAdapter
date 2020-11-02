package com.petterp.cloud.rvadapter.expand

import android.util.SparseArray
import androidx.annotation.LayoutRes

/**
 * @Author petterp
 * @Date 2020/10/28-11:08 AM
 * @Email ShiyihuiCloud@163.com
 * @Function 多ItemType 扩展
 * [spareItemTypes] 容器
 */
interface IMultiItemExpand {
    val spareItemTypes: SparseArray<Int>
        get() = SparseArray<@LayoutRes Int>(3)

    fun addView(itemType: Int, @LayoutRes layout: Int) {
        spareItemTypes.put(itemType, layout)
    }
}