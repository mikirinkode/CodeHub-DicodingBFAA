package com.mikirinkode.codehub.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.data.local.FavoriteUser
import com.mikirinkode.codehub.data.model.UsersResponse
import com.mikirinkode.codehub.databinding.ActivityFavoriteBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.main.UsersAdapter
import com.mikirinkode.codehub.ui.search.SearchActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter()
        usersAdapter.notifyDataSetChanged()
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        usersAdapter.setOnItemClickCallback(object: UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersResponse) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.username)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUsers.adapter = usersAdapter

            favoriteViewModel.getFavoriteUser()?.observe(this@FavoriteActivity,{
                if(it != null){
                    val list = mapList(it)
                    usersAdapter.setList(list)
                    if(list.isEmpty()){
                        tvNoData.visibility = View.VISIBLE
                        Toast.makeText(this@FavoriteActivity, "Favorite Kosong", Toast.LENGTH_SHORT).show()
                    } else {
                        tvNoData.visibility = View.GONE
                    }
                }

            })

            btnBack.setOnClickListener {
                onBackPressed()

            }

            floatBtnSearch.setOnClickListener {
                val moveToSearchActivity = Intent(this@FavoriteActivity, SearchActivity::class.java)
                startActivity(moveToSearchActivity)
            }
        }

    }

    private fun mapList(it: List<FavoriteUser>): ArrayList<UsersResponse> {
        val listUsers = ArrayList<UsersResponse>()
        for(user in it){
            val userMapped = UsersResponse(
                user.login,
                user.avatar_url,
                user.user_html,
                user.id
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }

}