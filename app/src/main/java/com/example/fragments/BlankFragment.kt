package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.data.dao.SozDao
import com.example.data.model.Soz
import com.example.database.SozDatabase
import com.example.mylugat.BottomSheetFragment
import com.example.mylugat.R
import com.example.mylugat.databinding.FragmentAboutBinding
import com.example.mylugat.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {
    private lateinit var dao: SozDao
    private lateinit var soz: Soz
    private lateinit var lotie: ImageView

    companion object {
        const val ID = "id"
    }
    lateinit var binding: FragmentBlankBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)
        val title: TextView = view.findViewById(R.id.title_blank_fragment)
        val description: TextView = view.findViewById(R.id.description_blank_fragment)
        lotie = view.findViewById(R.id.like_blank_fragment)
        dao = SozDatabase.getInstance(requireContext()).sozDao()

        val data = arguments
        val currentSozID = data!!.getInt(ID,0)
        val currentSozIsFavorite = data.getInt("key")
        Log.d("TT",currentSozIsFavorite.toString())

        soz = dao.getSozID(currentSozID)
        title.text = soz.name
        description.text = soz.description

        Log.d("TT", soz.toString())


        setFavoriteIcon()

        lotie.setOnClickListener {
            setFavorite()
            dao.update(soz)
            setFavoriteIcon()
        }
    }

    fun setFavorite() {
        if (0 == soz.is_favorite) {
            soz.is_favorite = 1
            Log.d("TTTT",soz.is_favorite.toString())
        }else {
            soz.is_favorite = 1 - soz.is_favorite!!
        }
    }

    fun setFavoriteIcon() {
        if (soz.is_favorite == 1) {
            lotie.setImageResource(R.drawable.ic_favorite)
        }else {
            lotie.setImageResource(R.drawable.ic_favorite_border)
        }
    }


}