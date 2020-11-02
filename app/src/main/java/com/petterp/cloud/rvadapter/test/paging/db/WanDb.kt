package com.petterp.cloud.rvadapter.test.paging.db

import android.content.Context
import androidx.room.Room
import com.petterp.cloud.rvadapter.test.KtxApplication

/**
 * @Author petterp
 * @Date 2020/11/2-6:06 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
object WanDb {

    val dao: WanDataBase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        buildDatabase(KtxApplication.context)
    }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            WanDataBase::class.java, "wan.db"
        )
            .build()
}