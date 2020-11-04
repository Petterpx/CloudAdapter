package com.cloud.adapter.base.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.cloud.adapter.base.BaseQuickAdapter;

/**
 * @Author petterp
 * @Date 2020/8/2-6:52 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
public interface OnItemLongClickListener {
    boolean onItemLongClick(
            @NonNull BaseQuickAdapter adapter,
            @NonNull View view,
            int position
    );
}
