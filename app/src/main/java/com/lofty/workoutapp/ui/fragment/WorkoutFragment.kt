package com.lofty.workoutapp.ui.fragment


import com.lofty.workoutapp.core.BaseFragment
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.FragmentWorkoutBinding

class WorkoutFragment : BaseFragment<FragmentWorkoutBinding, EmptyViewModel>() {
    override fun getViewBinding(): FragmentWorkoutBinding = FragmentWorkoutBinding.inflate(layoutInflater)

    override fun bindScreen() {

    }

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}