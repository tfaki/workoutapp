package com.lofty.workoutapp

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lofty.workoutapp.core.BaseAlphaActivity
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.ActivityMainBinding

class MainActivity : BaseAlphaActivity<ActivityMainBinding, EmptyViewModel>() {

    override fun bindScreen() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

}