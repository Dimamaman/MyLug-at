package com.example.fragments

import Kotlin.Contact
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.AbrrevativeAdapter
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentAbbrevationBinding

class AbbrevationFragment : Fragment(R.layout.fragment_abbrevation) {
    private lateinit var binding: FragmentAbbrevationBinding
    val abrrList = AbrrevativeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAbbrevationBinding.bind(view)

        abrrList.list = Contact.getAllAbrrevation()

        binding.apply {
            rcvAbrrevativeFragment.adapter = abrrList
            rcvAbrrevativeFragment.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            rcvAbrrevativeFragment.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,AboutFragment())?.commit()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}