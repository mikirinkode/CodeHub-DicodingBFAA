package com.mikirinkode.codehub.ui.detailuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mikirinkode.codehub.ui.detailuser.followers.FollowersFragment
import com.mikirinkode.codehub.ui.detailuser.following.FollowingFragment
import com.mikirinkode.codehub.ui.detailuser.profile.ProfileFragment

class SectionsPagerAdapter(activity: AppCompatActivity, data : Bundle) : FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ProfileFragment()
            1 -> fragment = FollowersFragment()
            2 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle

        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}