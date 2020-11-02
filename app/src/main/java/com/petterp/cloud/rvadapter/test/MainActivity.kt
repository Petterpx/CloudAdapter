package com.petterp.cloud.rvadapter.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petterp.cloud.rvadapter.R
import com.petterp.cloud.rvadapter.test.routine.activity.AdapterActivity
import com.petterp.cloud.rvadapter.test.routine.activity.AdapterMultiltemActivity
import com.petterp.cloud.rvadapter.test.paging.activity.PagingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdapter.setOnClickListener {
            startActivity(Intent(this, AdapterActivity::class.java))
        }
        btnType.setOnClickListener {
            startActivity(Intent(this, AdapterMultiltemActivity::class.java))
        }

        btnPagingType.setOnClickListener {

        }
        btnPagingAdapter.setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }
    }
}