package com.example.mygarage.ui.equipment.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R



class CameraRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var cameraName = itemView.findViewById<TextView>(R.id.cameraNameTxt)
    var cameraImage = itemView.findViewById<ImageView>(R.id.cameraImg)

}