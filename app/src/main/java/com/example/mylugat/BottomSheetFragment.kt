package com.example.mylugat

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.data.dao.SozDao
import com.example.data.model.Soz
import com.example.database.SozDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
    private lateinit var copy: ImageView

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
        copy = view.findViewById(R.id.copy)
        dao = SozDatabase.getInstance(requireContext()).sozDao()

        val data = arguments
        val currentSozID = data!!.getInt(ID, 0)
        val currentSozIsFavorite = data.getInt("key")
        Log.d("TT", currentSozIsFavorite.toString())



        soz = dao.getSozID(currentSozID)
        title.text = soz.name
        description.text = soz.description

        setFavoriteIcon()

        lotie.setOnClickListener {
            if (soz.is_favorite == 0) {
                Toast.makeText(context,"Tanlanganlarga qo'shildi",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context,"Tanlanganlardan o'shirildi",Toast.LENGTH_SHORT).show()
            }
            setFavorite()
        }

        copy.setOnClickListener {
            val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(soz.name,"${soz.name}\n ${soz.description}")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(),"Copyed",Toast.LENGTH_SHORT).show()
        }
    }


    private fun setFavorite() {
        soz.is_favorite = 1 - soz.is_favorite!!
        dao.updateSoz(soz)
        if (soz.is_favorite == 1) {
            (requireActivity() as MainActivity).count++
        } else {
            (requireActivity() as MainActivity).count--
        }
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