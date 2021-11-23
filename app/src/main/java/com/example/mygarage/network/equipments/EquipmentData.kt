package com.example.mygarage.network.equipments

data class EquipmentData(
    val cameras: List<Camera>,
    val others: List<Other>,
    val printers: List<Printer>,
    val vrHeadsets: List<VrHeadset>
)