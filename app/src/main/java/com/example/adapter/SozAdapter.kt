package com.example.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.Soz
import com.example.mylugat.BottomSheetFragment
import com.example.mylugat.R

class SozAdapter: RecyclerView.Adapter<SozAdapter.ViewHolder>() {
    var sozModelList: List<Soz> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val soz = itemView.findViewById<TextView>(R.id.tvSozName)
        val prefix = itemView.findViewById<TextView>(R.id.tvPrefix1)

        fun bindView(soz: Soz) {
            this.soz.text = soz.name

            itemView.setOnClickListener {
                listen.invoke(soz)
            }
            if (soz.name.length >= 2) {
                this.prefix.text = soz.name.substring(0,2).toUpperCase()
            }else {
                this.prefix.text = soz.name.substring(0,1).toUpperCase()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_user,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(sozModelList[position])
    }

    override fun getItemCount() = sozModelList.size

    var listen:  (soz: Soz) -> Unit = {}
    fun mySetOnClickListener(soz: (soz: Soz) -> Unit) {
        this.listen = soz
    }
}