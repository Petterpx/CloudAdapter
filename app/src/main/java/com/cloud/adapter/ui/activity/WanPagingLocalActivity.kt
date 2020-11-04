package com.cloud.adapter.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.ui.adapter.PagingWanAdapter
import com.cloud.adapter.data.viewmodel.WanPagingViewModel
import kotlinx.android.synthetic.main.activity_adater.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author petterp
 * @Date 2020/10/30-5:56 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class WanPagingLocalActivity : AppCompatActivity() {

    private val viewModel by viewModels<WanPagingViewModel>()

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val adapter = PagingWanAdapter()
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.netLocalPager().collectLatest {
                adapter.submitData(it)
            }
        }

    }

}