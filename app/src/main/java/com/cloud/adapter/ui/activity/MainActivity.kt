package com.cloud.adapter.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cloud.adapter.rvadapter.R
import kotlinx.android.synthetic.main.activity_main.*

/** 示例主Activity */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** 普通adapter */
        btnAdapter.setOnClickListener {
            startActivity(Intent(this, BookActivity::class.java))
        }

        /** 普通多类型adapter */
        btnType.setOnClickListener {
            startActivity(Intent(this, BookMultiltemActivity::class.java))
        }

        /** paging-adapter */
        btnPagingType.setOnClickListener {

        }

        /** paging-room-adapter */
        btnPagingNetToLocal.setOnClickListener {
            startActivity(Intent(this, WanPagingLocalActivity::class.java))
        }

        /** paging-多类型-adapter */
        btnPagingAdapter.setOnClickListener {
            startActivity(Intent(this, WanPagingActivity::class.java))
        }
    }
}

