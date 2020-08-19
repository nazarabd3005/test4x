package com.nazar.test4x.security.ui

import android.os.Bundle
import androidx.fragment.app.commit
import com.nazar.test4x.security.R
import com.nazar.test4x.utils.BaseSplitActivity
import dagger.hilt.android.AndroidEntryPoint

class InputCodeActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_code)

        supportFragmentManager.commit {
            replace(R.id.container,InputCodeFragment())
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {

    }
}