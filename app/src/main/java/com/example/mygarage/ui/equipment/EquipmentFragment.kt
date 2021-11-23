package com.example.mygarage.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mygarage.databinding.FragmentEquipmentBinding
import kotlinx.android.synthetic.main.fragment_equipment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EquipmentFragment : Fragment() {

    private val equipmentViewModel by viewModel<EquipmentViewModel>()

    private var _binding: FragmentEquipmentBinding? = null

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
        equipmentViewModel.equipmentList.observe(viewLifecycleOwner, {
            camerasTxt.text = it.cameras[0].name
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}