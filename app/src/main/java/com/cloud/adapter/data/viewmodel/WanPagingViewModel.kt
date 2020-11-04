package com.cloud.adapter.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.cloudx.core.LiveHttp
import com.cloud.adapter.api.ApiService
import com.cloud.adapter.data.WanEntity
import com.cloud.adapter.data.repository.WanRemoteMediator
import com.cloud.adapter.db.WanDb
import com.cloud.paging.ext.StatusRvData
import com.cloud.paging.ext.createIndexPagingSource
import com.cloud.paging.ext.createPager
import kotlinx.coroutines.flow.Flow

/**
 * @Author petterp
 * @Date 2020/10/30-5:46 PM
 * @Email ShiyihuiCloud@163.com
 * @Function pagingViewModel
 */
@OptIn(ExperimentalPagingApi::class)
class WanPagingViewModel : ViewModel() {

    private val api = LiveHttp.createApi(ApiService::class.java)

    /** 单网络adapter */
    fun netPager(): Flow<PagingData<WanEntity>> =
        //创建Pager
        createPager {
            //创建数据源
            createIndexPagingSource<WanEntity> { it ->
                try {
                    StatusRvData.Success(api.getList(it).data.datas.map {
                        WanEntity(it.id.toString(), it.title)
                    })
                } catch (e: Exception) {
                    StatusRvData.Error(e)
                }
            }
            // TODO: 2020/10/30 [cachedIn] 缓存数据，保证生命周期切换情况下，数据的完整性
        }.flow.cachedIn(viewModelScope)


    fun netLocalPager(): Flow<PagingData<WanEntity>> =
        createPager(remoteMediator = WanRemoteMediator(api)) {
            WanDb.dao.wanDao().queryPage()
        }.flow.cachedIn(viewModelScope)


}