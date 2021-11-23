package com.example.mygarage.ui.equipment.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R

class PrinterRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var printerName = itemView.findViewById<TextView>(R.id.printerNameTxt)
    var printerImage = itemView.findViewById<ImageView>(R.id.printerImg)

}