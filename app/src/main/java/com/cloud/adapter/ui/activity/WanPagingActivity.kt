package com.cloud.adapter.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.ui.adapter.PagingWanAdapter
import com.cloud.adapter.data.viewmodel.WanPagingViewModel
import com.cloud.adapter.ui.loadstate.WanPagingLoadStateAdapter
import kotlinx.android.synthetic.main.activity_adater.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author petterp
 * @Date 2020/10/28-5:54 PM
 * @Email ShiyihuiCloud@163.com
 * @Function 普通PagingActivity
 */
class WanPagingActivity : AppCompatActivity() {

    private val viewModel by viewModels<WanPagingViewModel>()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val adapter = PagingWanAdapter()
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = adapter.withLoadStateFooter(WanPagingLoadStateAdapter {

        })

        lifecycleScope.launch {
            viewModel.netPager().collectLatest {
                adapter.submitData(it)
            }
        }


    }

}