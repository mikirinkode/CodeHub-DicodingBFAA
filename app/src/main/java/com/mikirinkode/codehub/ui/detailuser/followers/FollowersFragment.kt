package com.mikirinkode.codehub.ui.detailuser.followers


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.R
import com.mikirinkode.codehub.databinding.FragmentFollowBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.detailuser.UserFollowsAdapter


class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserFollowsAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        val numOfFollowers = args?.getInt(DetailUserActivity.EXTRA_FOLLOWERS)

        adapter = UserFollowsAdapter()

        binding?.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(activity)
            rvUsers.adapter = adapter

            if (numOfFollowers == 0){
                tvNoDataFollows.visibility = View.VISIBLE
            } else {
                tvNoDataFollows.visibility = View.GONE
            }
        }
        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowersViewModel::class.java]
        searchFollowers()
    }

    private fun searchFollowers(){
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            } else {
                viewModel.setListFollowers(username)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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