package com.example.mygarage.ui.equipment.adaptars

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.equipments.Other
import com.example.mygarage.ui.equipment.EquipmentFragmentDirections
import com.example.mygarage.ui.equipment.viewholders.OtherRecyclerViewHolder




class OtherRecyclerViewAdapter(val context: Context, val others: List<Other>) : RecyclerView.Adapter<OtherRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.other_recycler_item,parent, false)
        return OtherRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OtherRecyclerViewHolder, position: Int) {
        val other = others[position]
        holder.otherName.text = other.name
        Glide.with(context).load(other.thumbnail).into(holder.otherImage)
        holder.itemView.setOnClickListener{ view ->
            val action = EquipmentFragmentDirections.actionNavigationEquipmentToReservationsFragment("#DED2F9",other.thumbnail,other.name,false,"","","")
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return others.size
    }


}
