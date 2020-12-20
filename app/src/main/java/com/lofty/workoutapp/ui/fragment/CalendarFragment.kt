package com.lofty.workoutapp.ui.fragment

import com.lofty.workoutapp.core.BaseFragment
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.FragmentCalendarBinding

class CalendarFragment : BaseFragment<FragmentCalendarBinding, EmptyViewModel>() {

    override fun getViewBinding(): FragmentCalendarBinding = FragmentCalendarBinding.inflate(layoutInflater)

    override fun bindScreen() {
        binding?.textView!!.text = "CALENDAR FRAGMENT"
    }

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}