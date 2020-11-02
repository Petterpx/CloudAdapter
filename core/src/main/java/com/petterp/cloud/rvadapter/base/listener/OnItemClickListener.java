package com.petterp.cloud.rvadapter.base.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.petterp.cloud.rvadapter.base.BaseQuickAdapter;

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
