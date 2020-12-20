package com.lofty.workoutapp.ui.fragment

import com.lofty.workoutapp.core.BaseFragment
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding, EmptyViewModel>() {
    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun bindScreen() {

    }

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}