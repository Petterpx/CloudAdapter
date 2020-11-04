package com.cloud.adapter.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cloud.adapter.data.WanEntity

/**
 * @Author petterp
 * @Date 2020/10/30-6:47 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@Dao
interface WanDao {

    @Query("SELECT * FROM wan")
    fun queryPage(): PagingSource<Int, WanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<WanEntity>)

    @Query("DELETE FROM wan")
    suspend fun clearRepos()
}