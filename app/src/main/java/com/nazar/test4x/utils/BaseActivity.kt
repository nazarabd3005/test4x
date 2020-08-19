package com.nazar.test4x.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazar.test4x.ui.root.RootAlertActivity
import com.scottyab.rootbeer.RootBeer

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootBeer = RootBeer(this)
//        if (rootBeer.isRooted){
//            startActivity(Intent(this,RootAlertActivity::class.java).also {
//                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            })
//        }
    }
}