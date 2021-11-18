package com.example.mygarage.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentRoomsBinding

class RoomsFragment : Fragment() {

    private lateinit var roomsViewModel: RoomsViewModel
    private var _binding: FragmentRoomsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        roomsViewModel =
            ViewModelProvider(this).get(RoomsViewModel::class.java)

        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reserveMedialabId.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_rooms_to_reservationsFragment)
        }
        binding.reserveWorkshopId.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_rooms_to_reservationsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}