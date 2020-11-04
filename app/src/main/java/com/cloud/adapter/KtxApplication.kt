package com.cloud.adapter

import android.app.Application
import android.content.Context
import com.cloudx.core.LiveConfig
import com.cloudx.core.interceptor.LogInterceptor
import com.cloud.paging.config.RvPagingConfig

/**
 * @Author petterp
 * @Date 2020/8/5-9:07 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class KtxApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        LiveConfig.baseUrl("https://www.wanandroid.com")
            .interceptor(LogInterceptor())
            .log()
        RvPagingConfig.build {

        }
    }
}