package com.example.mygarage.ui.equipment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.databinding.FragmentEquipmentBinding
import com.example.mygarage.ui.equipment.adaptars.CameraRecyclerViewAdapter
import com.example.mygarage.ui.equipment.adaptars.OtherRecyclerViewAdapter
import com.example.mygarage.ui.equipment.adaptars.PrinterRecyclerViewAdapter
import com.example.mygarage.ui.equipment.adaptars.VrRecyclerViewAdapter
import com.example.mygarage.ui.utils.ConnectivityCheck
import com.example.mygarage.ui.utils.InternetCheckDialog
import kotlinx.android.synthetic.main.fragment_equipment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EquipmentFragment : Fragment() {

    private val equipmentViewModel by viewModel<EquipmentViewModel>()

    private var _binding: FragmentEquipmentBinding? = null
    lateinit var printerAdapter: PrinterRecyclerViewAdapter
    lateinit var vrAdapter: VrRecyclerViewAdapter
    lateinit var cameraAdapter: CameraRecyclerViewAdapter
    lateinit var otherAdapter: OtherRecyclerViewAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val internetCheck =  ConnectivityCheck(requireContext())
        val internetCheckDialog = InternetCheckDialog(requireActivity())
        internetCheckDialog.startLoading()
        internetCheckDialog.isDismiss()

        internetCheck.observe(viewLifecycleOwner,{
            if(it == true){
                internetCheckDialog.isDismiss()
                equipmentViewModel.getEquipments()
            }
            else {
                internetCheckDialog.startLoading()
            }
        })




        binding.equipmentShimmerLayout.visibility = View.VISIBLE
        binding.equipmentConstraintLayout.visibility = View.GONE
        equipmentViewModel.equipmentList.observe(viewLifecycleOwner, {

            binding.equipmentShimmerLayout.apply {
                hideShimmer()
                visibility = View.GONE
            }
            binding.equipmentConstraintLayout.visibility = View.VISIBLE
            printerAdapter = PrinterRecyclerViewAdapter(requireContext(), it.printers)
            printersRecyclerview.adapter = printerAdapter
            printersRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            vrAdapter = VrRecyclerViewAdapter(requireContext(), it.vrHeadsets)
            vrRecyclerview.adapter = vrAdapter
            vrRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            cameraAdapter = CameraRecyclerViewAdapter(requireContext(), it.cameras)
            camerasRecyclerview.adapter = cameraAdapter
            camerasRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            otherAdapter = OtherRecyclerViewAdapter(requireContext(), it.others)
            othersRecyclerview.adapter = otherAdapter
            othersRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}