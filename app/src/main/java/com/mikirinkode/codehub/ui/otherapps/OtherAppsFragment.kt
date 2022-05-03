package com.mikirinkode.codehub.ui.otherapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
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
            setImage(ivKotakMovie, R.drawable.kotak_movie, "https://play.google.com/store/apps/details?id=com.mikirinkode.kotakmovie")
            setImage(ivSpod, R.drawable.spod, "https://play.google.com/store/apps/details?id=com.mikirinkode.spod")
            setImage(ivTextScanner, R.drawable.text_scanner, "https://github.com/mikirinkode/TextRecognition-Android")
            setImage(ivFinanciar, R.drawable.financiar, "https://github.com/mikirinkode/")
        }
    }

    private fun setImage(image: ImageView, drawable: Int, projectUrl: String) {
        Glide.with(requireContext())
            .load(drawable)
            .fitCenter()
            .into(image)
        image.setOnClickListener {
            val uri: Uri = Uri.parse(projectUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}