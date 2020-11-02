package com.petterp.cloud.rvadapter.base.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.petterp.cloud.rvadapter.base.BaseQuickAdapter;

/**
 * @Author petterp
 * @Date 2020/8/2-7:20 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
public interface OnItemChildLongClickListener {

    boolean onItemChildLongClick(@NonNull BaseQuickAdapter adapter,
                                 @NonNull View view,
                                 int position);
}
