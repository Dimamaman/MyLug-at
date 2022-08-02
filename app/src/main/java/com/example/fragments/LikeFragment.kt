package com.example.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.content.Context.MODE_PRIVATE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.LikeAdapter
import com.example.data.dao.SozDao
import com.example.data.model.Soz
import com.example.database.SozDatabase
import com.example.mylugat.MainActivity
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentLikeBinding

class LikeFragment : Fragment(R.layout.fragment_like) {
    private lateinit var binding: FragmentLikeBinding
    private lateinit var likeDao: SozDao
    private lateinit var likeAdapter: LikeAdapter
    private lateinit var listLikes: MutableList<Soz>
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        const val COUNT = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLikeBinding.bind(view)
        likeAdapter = LikeAdapter()
        listLikes = mutableListOf()
        val empty = view.findViewById<TextView>(R.id.empty)

        sharedPreferences = requireActivity().getSharedPreferences(COUNT, MODE_PRIVATE)

        likeDao = SozDatabase.getInstance(requireContext()).sozDao()
        listLikes.addAll(likeDao.getAllFavorites())
        likeAdapter.list = listLikes

        (activity as MainActivity).count = listLikes.size
        sharedPreferences.edit().putInt(COUNT,listLikes.size).apply()

        binding.apply {
            likeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            likeRecyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            likeRecyclerview.adapter = likeAdapter
        }

        likeAdapter.removeItemClick { soz, position ->
            setFavorite(soz)
            likeAdapter.removeItem(position)
            Toast.makeText(context,"Tanlanganlardan o'shirildi",Toast.LENGTH_SHORT).show()
            if (listLikes.isEmpty()) {
                empty.visibility = View.VISIBLE
            }
            (activity as MainActivity).count--
        }
        if (listLikes.isEmpty()) {
            empty.visibility = View.VISIBLE
        }
    }

    private fun setFavorite(soz: Soz) {
        soz.is_favorite = 1 - soz.is_favorite!!
        likeDao.updateSoz(soz)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,HomeFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,callBack
        )
    }
}