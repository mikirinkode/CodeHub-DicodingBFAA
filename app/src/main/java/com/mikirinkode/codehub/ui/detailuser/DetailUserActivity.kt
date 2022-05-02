package com.mikirinkode.codehub.ui.detailuser


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.mikirinkode.codehub.R
import com.mikirinkode.codehub.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL)
        val htmlUrl = intent.getStringExtra(EXTRA_HTML_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
//        bundle.putInt(DetailUserActivity.EXTRA_FOLLOWERS, it.followers)
//        bundle.putInt(DetailUserActivity.EXTRA_FOLLOWING, it.following)

        binding.apply {
            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailUserActivity, bundle)
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()

            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }


    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_FOLLOWERS = "extra_followers"
        const val EXTRA_FOLLOWING = "extra_following"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR_URL = "extra_avatar_url"
        const val EXTRA_HTML_URL = "extra_html_url"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.profile_tab_title,
            R.string.follower_tab_title,
            R.string.following_tab_title
        )
    }

}