package com.mikirinkode.codehub.ui.detailuser.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.R
import com.mikirinkode.codehub.databinding.FragmentFollowBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.detailuser.UserFollowsAdapter


class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserFollowsAdapter
    private lateinit var username: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)


        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        val numOfFollowing = args?.getInt(DetailUserActivity.EXTRA_FOLLOWING)

        adapter = UserFollowsAdapter()

        binding?.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(activity)
            rvUsers.adapter = adapter

            if (numOfFollowing == 0){
                tvNoDataFollows.visibility = View.VISIBLE
            } else {
                tvNoDataFollows.visibility = View.GONE
            }
        }
        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        searchFollowing()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchFollowing(){
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)

            }
        })
    }


    private fun showLoading(state: Boolean) {
        binding?.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

}