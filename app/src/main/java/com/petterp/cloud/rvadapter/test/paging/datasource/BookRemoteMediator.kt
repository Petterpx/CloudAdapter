package com.petterp.cloud.rvadapter.test.paging.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.petterp.cloud.rvadapter.test.net.ApiServiceImpl
import com.petterp.cloud.rvadapter.test.net.WanListBean
import com.petterp.cloud.rvadapter.test.paging.db.WanDataBase
import com.petterp.cloud.rvadapter.test.paging.db.WanDb

/**
 * @Author petterp
 * @Date 2020/10/30-6:25 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
@ExperimentalPagingApi
class BookRemoteMediator : RemoteMediator<Int, WanListBean.Data>() {
    var position = 0
    override suspend fun load(loadType: LoadType, state: PagingState<Int, WanListBean.Data>): MediatorResult {
        when (loadType) {
            // TODO: 2020/10/30 初始化刷新
            LoadType.REFRESH -> {
                WanDb.dao.wanDao().clearRepos()
                position = 0
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // TODO: 2020/10/30 列表头部添加数据
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // TODO: 2020/10/30 加载更多
            LoadType.APPEND -> {
                ++position
            }
        }
        return try {
            val data = ApiServiceImpl.apiService.getMainList(position).data.datas
            WanDb.dao.withTransaction {
                WanDb.dao.wanDao().insertAll(data)
            }
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }
}