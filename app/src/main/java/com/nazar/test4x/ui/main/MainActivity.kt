package com.nazar.test4x.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.nazar.test4x.BuildConfig
import com.nazar.test4x.R
import com.nazar.test4x.core.pref.SharePref
import com.nazar.test4x.databinding.ActivityMainBinding
import com.nazar.test4x.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var sharePref: SharePref



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)

        if (sharePref.loadPreferences(SharePref.MINUTES_TO_LOCK) == "") {
            sharePref.savePreferences(SharePref.MINUTES_TO_LOCK, "2")
        }
    }
}
