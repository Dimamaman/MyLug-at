package com.example.mylugat

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.data.dao.SozDao
import com.example.data.model.Soz
import com.example.database.SozDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.net.IDN

class BottomSheetFragment : BottomSheetDialogFragment() {
    override fun getTheme(): Int {
        return R.style.BottomSheetTheme
    }

    companion object {
        const val ID = "id"
    }

    private lateinit var dao: SozDao
    private lateinit var soz: Soz
    private lateinit var lotie: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: TextView = view.findViewById(R.id.title_bottom_sheet)
        val description: TextView = view.findViewById(R.id.description_bottom_sheet)
        lotie = view.findViewById(R.id.like_lottie)
        dao = SozDatabase.getInstance(requireContext()).sozDao()

        val data = arguments
        val currentSozID = data!!.getInt(ID, 0)
        val currentSozIsFavorite = data.getInt("key")
        Log.d("TT", currentSozIsFavorite.toString())

        soz = dao.getSozID(currentSozID)
        title.text = soz.name
        description.text = soz.description

        Log.d("TT", soz.toString())

        setFavoriteIcon()

        lotie.setOnClickListener {
            setFavorite()
        }
    }


    private fun setFavorite() {
        soz.is_favorite = 1 - soz.is_favorite!!
        dao.update(soz)
        setFavoriteIcon()
    }

    private fun setFavoriteIcon() {
        if (soz.is_favorite == 1) {
            lotie.setImageResource(R.drawable.ic_favorite)
        } else {
            lotie.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}