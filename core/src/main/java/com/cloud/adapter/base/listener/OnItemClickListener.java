package com.cloud.adapter.base.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.cloud.adapter.base.BaseQuickAdapter;

/**
 * @Author petterp
 * @Date 2020/8/2-6:37 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
public interface OnItemClickListener {
    void onItemClick(
            @NonNull BaseQuickAdapter adapter,
            @NonNull View view,
            int position
    );
}
