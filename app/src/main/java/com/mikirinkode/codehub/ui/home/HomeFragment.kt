package com.mikirinkode.codehub.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.codehub.data.model.UserEntity
import com.mikirinkode.codehub.databinding.FragmentHomeBinding
import com.mikirinkode.codehub.ui.detailuser.DetailUserActivity
import com.mikirinkode.codehub.ui.main.UsersAdapter
import com.mikirinkode.codehub.ui.search.SearchActivity
import com.mikirinkode.codehub.utils.DataMapper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersAdapter = UsersAdapter()
        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

        usersAdapter.setOnItemClickCallback(object: UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserEntity) {
                Intent(requireContext(), DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.username)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_HTML_URL, data.htmlUrl)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = usersAdapter

            searchFabBtn.setOnClickListener {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                startActivity(intent)
            }
        }

        homeViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }
        homeViewModel.onFailure.observe(requireActivity()) {
            onFailure(it)
        }

        homeViewModel.getListUsers().observe(requireActivity()) {
            if (it != null) {
                onFailure(false)
                usersAdapter.setList(DataMapper.mapResponsesToEntities(it))
            }
        }
        refreshApp()
    }

    private fun refreshApp() {
        binding.apply {
            swipeToRefresh.setOnRefreshListener {
                homeViewModel.findUsers()
                swipeToRefresh.isRefreshing = false
            }
        }
    }


    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun onFailure(fail: Boolean) {
        binding.tvOnFailMsg.visibility = if (fail) View.VISIBLE else View.GONE

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}