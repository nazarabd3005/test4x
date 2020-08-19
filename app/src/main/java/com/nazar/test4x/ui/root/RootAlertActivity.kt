package com.nazar.test4x.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.nazar.test4x.R

class RootAlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        supportFragmentManager.commit {
            replace(R.id.container,RootAlertFragment())
            addToBackStack(null)
        }
    }
}