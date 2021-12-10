package com.example.mygarage.ui.equipment.adaptars

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.equipments.Camera
import com.example.mygarage.ui.equipment.EquipmentFragmentDirections
import com.example.mygarage.ui.equipment.viewholders.CameraRecyclerViewHolder
import com.example.mygarage.ui.home.HomeFragmentDirections

class CameraRecyclerViewAdapter(val context: Context, val cameras: List<Camera>) : RecyclerView.Adapter<CameraRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.camera_recycler_item,parent, false)
        return CameraRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CameraRecyclerViewHolder, position: Int) {
        val camera = cameras[position]
        holder.cameraName.text = camera.name
        Glide.with(context).load(camera.thumbnail).into(holder.cameraImage)
        holder.itemView.setOnClickListener{ view ->
            val action = EquipmentFragmentDirections.actionNavigationEquipmentToReservationsFragment("#F5D4C1",camera.thumbnail,camera.name,false,"","","")
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return cameras.size
    }


}
