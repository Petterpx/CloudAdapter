package com.petterp.cloud.rvadapter.test.paging.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petterp.cloud.rvadapter.test.net.WanListBean

/**
 * @Author petterp
 * @Date 2020/10/30-6:47 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@Dao
interface WanDao {

    @Query("SELECT * FROM wan")
    suspend fun queryPage(): PagingSource<Int, WanListBean.Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<WanListBean.Data>)

    @Query("DELETE FROM wan")
    suspend fun clearRepos()
}