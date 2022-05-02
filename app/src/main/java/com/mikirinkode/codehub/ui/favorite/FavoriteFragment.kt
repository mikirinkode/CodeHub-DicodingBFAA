package com.mikirinkode.codehub.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.data.model.FavoriteUser
import com.mikirinkode.codehub.data.source.remote.responses.UserResponse
import com.mikirinkode.codehub.databinding.FragmentFavoriteBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.main.UsersAdapter


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersAdapter = UsersAdapter()
        usersAdapter.notifyDataSetChanged()
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        usersAdapter.setOnItemClickCallback(object: UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponse) {
                Intent(requireContext(), DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.username)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
                    startActivity(it)
                }
            }

        })
        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
            rvUsers.adapter = usersAdapter

            favoriteViewModel.getFavoriteUser()?.observe(requireActivity()) {
                if (it != null) {
                    val list = mapList(it)
                    usersAdapter.setList(list)
                    if (list.isEmpty()) {
                        tvNoData.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), "Favorite Kosong", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        tvNoData.visibility = View.GONE
                    }
                }

            }
        }
    }

    private fun mapList(it: List<FavoriteUser>): ArrayList<UserResponse> {
        val listUsers = ArrayList<UserResponse>()
        for(user in it){
            val userMapped = UserResponse(
                user.login,
                user.avatar_url,
                user.user_html,
                user.id
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}