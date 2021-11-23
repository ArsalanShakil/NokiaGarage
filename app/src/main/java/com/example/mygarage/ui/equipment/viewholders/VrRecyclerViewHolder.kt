package com.example.mygarage.ui.equipment.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R


class VrRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var vrName = itemView.findViewById<TextView>(R.id.vrNameTxt)
    var vrImage = itemView.findViewById<ImageView>(R.id.vrImg)

}