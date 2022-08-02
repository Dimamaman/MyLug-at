package com.example.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentAboutBinding
import com.example.mylugat.databinding.FragmentAboutBookBinding

class AboutBook : Fragment(R.layout.fragment_about_book) {
    private lateinit var binding: FragmentAboutBookBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBookBinding.bind(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,AboutFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callBack
        )
    }
}