package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.SozAdapter
import com.example.data.dao.SozDao
import com.example.database.SozDatabase
import com.example.mylugat.BottomSheetFragment
import com.example.mylugat.MainActivity
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sozDao: SozDao
    private lateinit var sozAdapter: SozAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        sozDao = SozDatabase.getInstance(requireContext()).sozDao()

        binding.apply {
            rvSoz.layoutManager = LinearLayoutManager(requireContext())
            rvSoz.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        sozAdapter = SozAdapter()
        binding.rvSoz.adapter = sozAdapter
        sozAdapter.sozModelList = sozDao.getAllSoz()


        sozAdapter.mySetOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            val bundle = Bundle()
            bundle.putInt("key", it.is_favorite!!)
            bundle.putInt(BottomSheetFragment.ID,it.id)
            bottomSheetFragment.arguments = bundle
            (context as MainActivity).supportFragmentManager.let { it1 ->
                bottomSheetFragment.show(it1,"")
            }
        }



        binding.llToolbar1.addTextChangedListener {
            it?.let {
                val searchSoz = it.toString()
                val newList = sozDao.searchSoz("%$searchSoz%")
                sozAdapter.sozModelList = newList
            }
        }
    }

}