package com.example.mygarage.ui.equipment.adaptars

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.equipments.Printer
import com.example.mygarage.ui.equipment.EquipmentFragmentDirections
import com.example.mygarage.ui.equipment.viewholders.PrinterRecyclerViewHolder


class PrinterRecyclerViewAdapter(val context: Context, val printers: List<Printer>) : RecyclerView.Adapter<PrinterRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrinterRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.printer_recycler_item,parent, false)
        return PrinterRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrinterRecyclerViewHolder, position: Int) {
        val printer = printers[position]
        holder.printerName.text = printer.name
        Glide.with(context).load(printer.thumbnail).into(holder.printerImage)
        holder.itemView.setOnClickListener{ view ->
            val action = EquipmentFragmentDirections.actionNavigationEquipmentToReservationsFragment("#D3E1FF",printer.thumbnail,printer.name,false,"","","")
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return printers.size
    }


}
