package com.mikirinkode.codehub.ui.otherapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mikirinkode.codehub.R
import com.mikirinkode.codehub.databinding.FragmentOtherAppsBinding


class OtherAppsFragment : Fragment() {

    private var _binding: FragmentOtherAppsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtherAppsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setImage(ivKotakMovie, R.drawable.kotak_movie)
            setImage(ivSpod, R.drawable.spod)
            setImage(ivTextScanner, R.drawable.text_scanner)
            setImage(ivFinanciar, R.drawable.financiar)
        }
    }

    private fun setImage(image: ImageView, drawable: Int) {
        Glide.with(requireContext())
            .load(drawable)
            .fitCenter()
            .into(image)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}