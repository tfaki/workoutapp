package com.lofty.workoutapp.ui.fragment

import com.lofty.workoutapp.core.BaseFragment
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.FragmentCurrentHealthStatusBinding

class CurrentHealthStatusFragment : BaseFragment<FragmentCurrentHealthStatusBinding, EmptyViewModel>() {
    override fun getViewBinding(): FragmentCurrentHealthStatusBinding = FragmentCurrentHealthStatusBinding.inflate(layoutInflater)

    override fun bindScreen() {

    }

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}