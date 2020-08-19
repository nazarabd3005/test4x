package com.nazar.test4x.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nazar.test4x.BuildConfig
import com.nazar.test4x.R
import com.nazar.test4x.core.pref.SharePref
import com.nazar.test4x.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity(){

    companion object {
        const val LOCK_SCREEN_RESULT = 578
    }

    @Inject lateinit var sharePref: SharePref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            if(sharePref.loadBoolPreferences(SharePref.IS_APP_LOCK_ACTIVE)) {
                val intent = Intent()
                intent.setClassName(BuildConfig.APPLICATION_ID, "com.nazar.test4x.security.ui.InputCodeActivity")
                startActivityForResult(intent, LOCK_SCREEN_RESULT)
            }else{
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }, 1000)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCK_SCREEN_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                val returnValue: String = data!!.getStringExtra("lockscreen")
                if (returnValue.equals("finished")) {
                    Toast.makeText(this, "You have unlocked app successfully", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}