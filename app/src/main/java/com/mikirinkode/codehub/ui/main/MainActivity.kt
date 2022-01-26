package com.mikirinkode.codehub.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.data.model.UsersResponse
import com.mikirinkode.codehub.databinding.ActivityMainBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.favorite.FavoriteActivity
import com.mikirinkode.codehub.ui.main.settings.MainViewModel
import com.mikirinkode.codehub.ui.main.settings.SettingPreferences
import com.mikirinkode.codehub.ui.main.settings.ViewModelFactory
import com.mikirinkode.codehub.ui.search.SearchActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)


        usersAdapter = UsersAdapter()
        usersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UsersViewModel::class.java]


        usersViewModel.isLoading.observe(this, {
            showLoading(it)
        })
        usersViewModel.onFailure.observe(this, {
            onFailure(it)
        })


        usersViewModel.getListUsers().observe(this, {
            if (it != null) {
                onFailure(false)
                usersAdapter.setList(it)
            }
        })


        usersAdapter.setOnItemClickCallback(object: UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersResponse) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.username)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_HTML_URL, data.htmlUrl)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = usersAdapter

            val pref = SettingPreferences.getInstance(dataStore)
            val mainViewModel = ViewModelProvider(this@MainActivity, ViewModelFactory(pref))[MainViewModel::class.java]
            mainViewModel.getThemeSettings().observe(this@MainActivity, {
                    isDarkModeActive: Boolean ->
                if (isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })
            switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked:Boolean ->
                mainViewModel.saveThemeSetting(isChecked)

            }

            btnFav.setOnClickListener{
                val moveToFavActivity = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(moveToFavActivity)
            }

            floatBtnSearch.setOnClickListener {
                val moveToSearchActivity = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(moveToSearchActivity)
            }
        }

        refreshApp()
    }


    private fun refreshApp() {
        binding.apply {
            swipeToRefresh.setOnRefreshListener {
                usersViewModel.findUsers()
                swipeToRefresh.isRefreshing = false
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


}