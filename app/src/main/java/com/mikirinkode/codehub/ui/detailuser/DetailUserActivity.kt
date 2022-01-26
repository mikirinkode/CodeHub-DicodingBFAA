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
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL)
        val htmlUrl = intent.getStringExtra(EXTRA_HTML_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this)[DetailUserViewModel::class.java]

        viewModel.setUserDetail(username.toString())

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })
        viewModel.onFailure.observe(this, {
            onFailure(it)
        })
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {

                    bundle.putInt(EXTRA_FOLLOWERS, it.followers)
                    bundle.putInt(EXTRA_FOLLOWING, it.following)

                    tvDetailName.text = it.name
                    tvDetailUsername.text = it.login
                    tvDetailFollowers.text = it.followers.toString()
                    tvDetailFollowing.text = it.following.toString()
                    tvDetailRepository.text = it.publicRepos.toString()
                    tvDetailCompany.text = it.company ?: "-"
                    tvDetailLocation.text = it.location ?: "-"

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .into(ivDetailProfile)

                }
            }
        })

        binding.apply {
            var isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = viewModel.checkUser(id)
                withContext(Dispatchers.Main){
                    if(count != null){
                        if(count > 0){
                            toggleFavorite.isChecked = true
                            isChecked = true
                        } else {
                            toggleFavorite.isChecked = false
                            isChecked = false
                        }
                    }
                }
            }

            toggleFavorite.setOnClickListener{
                isChecked = !isChecked
                if(isChecked){
                    viewModel.addToFavorite(username.toString(), id, avatarUrl.toString(), htmlUrl.toString())
                    Toast.makeText(this@DetailUserActivity, "menambahkan ke favorite", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.removeFromFavorite(id)
                    Toast.makeText(this@DetailUserActivity, "menghapus dari favorite", Toast.LENGTH_SHORT).show()
                }
                toggleFavorite.isChecked = isChecked
            }


            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailUserActivity, bundle)
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnRefresh.setOnClickListener {
                viewModel.setUserDetail(username.toString())
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,"$username on CodeHub: $htmlUrl")
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent,"Share To:"))
            }
        }

    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun onFailure(fail: Boolean) {
        binding.apply {
            if (fail) {
                tvOnFailMsg.visibility = View.VISIBLE
            } else {
                tvOnFailMsg.visibility = View.GONE
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
            R.string.follower_tab_title,
            R.string.following_tab_title
        )
    }

}