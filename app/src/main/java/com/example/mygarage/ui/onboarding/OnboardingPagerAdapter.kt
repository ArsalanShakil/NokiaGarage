package com.example.mygarage.ui.onboarding

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.databinding.ItemOnboardingScreenBinding
import com.google.android.material.bottomappbar.BottomAppBar

class OnboardingPagerAdapter(private val items: ArrayList<OnboardingData>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SHOW_IMAGE_ON_TOP -> {
                TopViewHolder(
                    ItemOnboardingScreenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                TopViewHolder(
                    ItemOnboardingScreenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is TopViewHolder -> holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return SHOW_IMAGE_ON_TOP
    }

    companion object{
        const val SHOW_IMAGE_ON_TOP = 1
    }

    class TopViewHolder(private val binding: ItemOnboardingScreenBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: OnboardingData){
                    binding.txtTitle.text = data.title
                    binding.txtDescription.text = data.description
                    binding.imageView.setImageResource(data.image)
                }
            }
}