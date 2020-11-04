package com.cloud.adapter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.adapter.rvadapter.R
import com.cloud.adapter.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_adater.*

/**
 * @Author petterp
 * @Date 2020/10/28-5:54 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adater)
        val datas = mutableListOf<String>()
        (0..20).forEach { _ ->
            datas.add("Android艺术探索")
        }
        rvMain.adapter = BookAdapter(datas)
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}