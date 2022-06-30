package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentLikeBinding

class LikeFragment : Fragment(R.layout.fragment_like) {
    private lateinit var binding: FragmentLikeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLikeBinding.bind(view)

        val count = arguments


    }
}