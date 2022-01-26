package com.mikirinkode.codehub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikirinkode.codehub.databinding.ActivitySplashScreenBinding
import com.mikirinkode.codehub.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvAppName.alpha = 0f
            tvAppName.animate().setDuration(1500).alpha(1f).withEndAction {
                val moveToMainActivity = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(moveToMainActivity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}