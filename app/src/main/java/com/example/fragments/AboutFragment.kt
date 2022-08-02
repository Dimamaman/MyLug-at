package com.example.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.example.mylugat.MainActivity
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentAboutBinding
import com.example.mylugat.databinding.FragmentAboutBookBinding
import com.example.mylugat.databinding.FragmentLikeBinding
import kotlin.concurrent.fixedRateTimer

class AboutFragment : Fragment(R.layout.fragment_about) {
    private lateinit var binding: FragmentAboutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)

        binding.apply {
            aboutBook.setOnClickListener {
                fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, AboutBook())
                    ?.commit()
            }
            abrrevationBook.setOnClickListener {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, AbbrevationFragment())?.commit()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,HomeFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,callBack)
    }

}