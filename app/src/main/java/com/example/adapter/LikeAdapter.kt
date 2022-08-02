package com.example.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.Soz
import com.example.mylugat.R

class LikeAdapter: RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {
    var list = mutableListOf<Soz>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class LikeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val soz = itemView.findViewById<TextView>(R.id.tvSozName)
        val prefix = itemView.findViewById<TextView>(R.id.tvPrefix1)
        val like = itemView.findViewById<ImageView>(R.id.like_icon)

        fun bindView(soz: Soz) {
            this.soz.text = soz.name

            if (soz.name.length >= 2) {
                this.prefix.text = soz.name.substring(0,2).toUpperCase()
            }else {
                this.prefix.text = soz.name.substring(0,1).toUpperCase()
            }
            like.setOnClickListener {
                setLikeImage(it as ImageView)
                onClick.invoke(soz,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.like_item,parent,false)
        return LikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount() = list.size

    var onClick: (soz: Soz,position: Int) -> Unit = {soz, position ->}
    fun removeItemClick(itemClick: (soz: Soz,position: Int) -> Unit) {
        this.onClick = itemClick
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setLikeImage(view: ImageView) {
        view.setImageResource(R.drawable.ic_favorite_border)
    }
}