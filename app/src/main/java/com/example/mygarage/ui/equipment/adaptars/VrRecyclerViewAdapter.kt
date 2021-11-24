package com.example.mygarage.ui.equipment.adaptars

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.equipments.Printer
import com.example.mygarage.network.equipments.VrHeadset
import com.example.mygarage.ui.equipment.EquipmentFragmentDirections
import com.example.mygarage.ui.equipment.viewholders.PrinterRecyclerViewHolder
import com.example.mygarage.ui.equipment.viewholders.VrRecyclerViewHolder



class VrRecyclerViewAdapter(val context: Context, val vrheadsets: List<VrHeadset>) : RecyclerView.Adapter<VrRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VrRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.vr_recycler_item,parent, false)
        return VrRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: VrRecyclerViewHolder, position: Int) {
        val vrheadset = vrheadsets[position]
        holder.vrName.text = vrheadset.name
        Glide.with(context).load(vrheadset.thumbnail).into(holder.vrImage)
        holder.itemView.setOnClickListener{ view ->
            val action = EquipmentFragmentDirections.actionNavigationEquipmentToReservationsFragment("#D0F1EB",vrheadset.thumbnail,vrheadset.name)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return vrheadsets.size
    }


}
