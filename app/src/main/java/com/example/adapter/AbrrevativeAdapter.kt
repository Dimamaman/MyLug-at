package com.example.adapter

import Kotlin.Abrrevation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.AbbrevationFragment
import com.example.mylugat.R

class AbrrevativeAdapter: RecyclerView.Adapter<AbrrevativeAdapter.AbrrevativeViewHolder>() {
    var list: List<Abrrevation> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class AbrrevativeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val abrrTitle: TextView = itemView.findViewById(R.id.abrrevative_title)
        val abrrDescription: TextView = itemView.findViewById(R.id.abrrevative_description)

        fun bind(abrrevation: Abrrevation) {
            abrrTitle.text = abrrevation.abrrevate
            abrrDescription.text = abrrevation.description

            itemView.setOnClickListener {
                if (abrrDescription.visibility == View.GONE) {
                    abrrDescription.visibility = View.VISIBLE
                }else {
                    abrrDescription.visibility = View.GONE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbrrevativeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.abrrevative_item,parent,false)
        return AbrrevativeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbrrevativeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}