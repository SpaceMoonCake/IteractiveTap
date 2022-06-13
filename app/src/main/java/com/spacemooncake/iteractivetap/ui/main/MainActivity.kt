package com.spacemooncake.iteractivetap.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spacemooncake.iteractivetap.R
import com.spacemooncake.iteractivetap.ui.video.InteractiveFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, InteractiveFragment.newInstance())
                .commitNow()
        }
    }
}