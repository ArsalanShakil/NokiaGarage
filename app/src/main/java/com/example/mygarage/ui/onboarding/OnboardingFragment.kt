package com.example.mygarage.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mygarage.R
import com.example.mygarage.databinding.ActivityMainBinding
import com.example.mygarage.databinding.FragmentHomeBinding
import com.example.mygarage.databinding.FragmentOnboardingBinding
import com.example.mygarage.ui.home.HomeViewModel

class OnboardingFragment : Fragment() {

    private lateinit var onboardingViewModel: OnboardingViewModel
    private var _binding: FragmentOnboardingBinding? = null
    private var itemList = ArrayList<OnboardingData>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onboardingViewModel =
            ViewModelProvider(this).get(OnboardingViewModel::class.java)

        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        binding.txtPrevious.setOnClickListener {
            val currentItemPosition = binding.viewPager.currentItem
            if(currentItemPosition == 0) {

            }
            binding.viewPager.setCurrentItem(currentItemPosition -1, true)

        }
        binding.txtNext.setOnClickListener {
            val currentItemPosition = binding.viewPager.currentItem
            if (currentItemPosition == itemList.size -1){
                Log.d("txt","something")
            }
            binding.viewPager.setCurrentItem(currentItemPosition + 1,true)
        }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViewPager(){
        itemList = getItems()
        binding.viewPager.adapter = OnboardingPagerAdapter(itemList)
        binding.wormDot.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private  var pageChangeCallback : ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.txtPrevious.visibility = View.INVISIBLE.takeIf { position == 0 }?:View.VISIBLE
        }
    }

    private fun getItems() : ArrayList<OnboardingData>{
        val items = ArrayList<OnboardingData>()
        items.add(OnboardingData(resources.getString(R.string.onboarding_title1),resources.getString(R.string.onboarding_description1),R.drawable.ic_onboarding2))
        items.add(OnboardingData(resources.getString(R.string.onboarding_title2),resources.getString(R.string.onboarding_description2),R.drawable.ic_onboarding3))
        items.add(OnboardingData(resources.getString(R.string.onboarding_title3),resources.getString(R.string.onboarding_description3),R.drawable.ic_onboarding4))
        items.add(OnboardingData(resources.getString(R.string.onboarding_title4),resources.getString(R.string.onboarding_description4),R.drawable.ic_onboarding5))
        return  items
    }
}