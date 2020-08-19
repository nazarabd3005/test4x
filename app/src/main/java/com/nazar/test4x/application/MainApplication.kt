package com.nazar.test4x.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.play.core.splitcompat.SplitCompat
import com.nazar.test4x.BuildConfig
import com.nazar.test4x.core.pref.SharePref
import com.nazar.test4x.ui.main.MainActivity
import com.nazar.test4x.ui.root.RootAlertActivity
import com.nazar.test4x.ui.splash.SplashScreenActivity
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {


    @Inject lateinit var sharePref: SharePref

    override fun onCreate() {
        super.onCreate()
        setupActivityLifeCycleCallback()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    private fun setupActivityLifeCycleCallback(){
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
                //prevent opening lock screen opening again and again
                if (sharePref.loadPreferences(SharePref.LOCK_PAUSE_TIME).isEmpty()) {
                    sharePref.savePreferences(SharePref.LOCK_PAUSE_TIME, "0")
                }
                val pauseTime = sharePref.loadPreferences(SharePref.LOCK_PAUSE_TIME).toLong()
                val currentTime = System.currentTimeMillis()
                var LOCK_INTERVAL_TIME: Long = 0
                if (pauseTime > 0 && sharePref.loadPreferences(SharePref.MINUTES_TO_LOCK).isNotEmpty()) {
                    LOCK_INTERVAL_TIME = (sharePref.loadPreferences(SharePref.MINUTES_TO_LOCK).toInt() * 60 * 1000).toLong()
                }
                if (activity !is SplashScreenActivity && activity !is RootAlertActivity && activity.javaClass.canonicalName  != "com.nazar.test4x.security.ui.InputCodeActivity"
                    && sharePref.loadBoolPreferences(SharePref.IS_APP_LOCK_ACTIVE)
                    && pauseTime > 0 && sharePref.loadPreferences(SharePref.MINUTES_TO_LOCK).isNotEmpty()
                    && currentTime - pauseTime >= LOCK_INTERVAL_TIME) {
                    val intent = Intent()
                    intent.setClassName(BuildConfig.APPLICATION_ID, "com.nazar.test4x.security.ui.InputCodeActivity")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }

            override fun onActivityPaused(activity: Activity) {
                if (activity !is SplashScreenActivity && activity !is RootAlertActivity && activity.javaClass.canonicalName  != "com.nazar.test4x.security.ui.InputCodeActivity"
                    && sharePref.loadBoolPreferences(SharePref.IS_APP_LOCK_ACTIVE)) {
                    sharePref.savePreferences(SharePref.LOCK_PAUSE_TIME, System.currentTimeMillis().toString() + "")
                } else if (activity.javaClass.canonicalName  != "com.nazar.test4x.security.ui.InputCodeActivity") {
                    sharePref.savePreferences(SharePref.LOCK_PAUSE_TIME, "0")
                }
            }

            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}