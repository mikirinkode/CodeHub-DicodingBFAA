package com.mikirinkode.codehub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.mikirinkode.codehub.R
import com.mikirinkode.codehub.databinding.ActivitySplashScreenBinding
import com.mikirinkode.codehub.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        val isDarkModeActive = preference.getBoolean(resources.getString(R.string.key_dark_mode), false)
        updateTheme(isDarkModeActive)

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

    private fun updateTheme(isDarkModeActive: Boolean) {
        if (isDarkModeActive) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}