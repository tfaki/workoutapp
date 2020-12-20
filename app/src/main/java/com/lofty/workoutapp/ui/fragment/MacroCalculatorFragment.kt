package com.lofty.workoutapp.ui.fragment

import com.lofty.workoutapp.core.BaseFragment
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.FragmentMacroCalculatorBinding

class MacroCalculatorFragment : BaseFragment<FragmentMacroCalculatorBinding, EmptyViewModel>() {
    override fun getViewBinding(): FragmentMacroCalculatorBinding = FragmentMacroCalculatorBinding.inflate(layoutInflater)

    override fun bindScreen() {

    }

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}