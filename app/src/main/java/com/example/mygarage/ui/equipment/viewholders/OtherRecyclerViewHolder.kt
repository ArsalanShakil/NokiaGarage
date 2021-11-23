package com.example.mygarage.ui.equipment.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R



class OtherRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var otherName = itemView.findViewById<TextView>(R.id.otherNameTxt)
    var otherImage = itemView.findViewById<ImageView>(R.id.otherImg)

}
