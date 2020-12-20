package com.lofty.workoutapp.ui.activity

import com.lofty.workoutapp.R
import com.lofty.workoutapp.core.BaseAlphaActivity
import com.lofty.workoutapp.core.EmptyViewModel
import com.lofty.workoutapp.databinding.ActivityWelcomeBinding
import com.lofty.workoutapp.helper.setOnClickListeners
import com.lofty.workoutapp.helper.startActivity
import com.lofty.workoutapp.util.Const
import com.mikhaellopez.rxanimation.RxAnimation
import com.mikhaellopez.rxanimation.fadeIn
import com.mikhaellopez.rxanimation.fadeOut
import com.uber.autodispose.autoDisposable

class WelcomeActivity : BaseAlphaActivity<ActivityWelcomeBinding, EmptyViewModel>() {

    override fun getViewBinding(): ActivityWelcomeBinding = ActivityWelcomeBinding.inflate(layoutInflater)
    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

    override fun bindScreen() {
        setAnimation()
        arrangeListener()
    }

    private fun arrangeListener() {
        setOnClickListeners(listOf(binding.buttonLogin)) {
            startActivity<LoginActivity> {
                putExtra(Const.BundleName.SCREEN_TYPE, ScreenType.LOGIN_TYPE.value)
            }
        }

        setOnClickListeners(listOf(binding.buttonRegister)) {
            startActivity<LoginActivity> {
                putExtra(Const.BundleName.SCREEN_TYPE, ScreenType.REGISTER_TYPE.value)
            }
        }
    }

    private fun setAnimation() {
        RxAnimation.together(
                binding.buttonLogin.fadeOut(0L),
                binding.buttonRegister.fadeOut(0L)
        )
                .autoDisposable(scopeProvider)
                .subscribe()

        RxAnimation.sequentially(
                binding.buttonLogin.fadeIn(500L),
                binding.buttonRegister.fadeIn(500L)
        )
                .autoDisposable(scopeProvider)
                .subscribe()
    }

    override fun onStart() {
        this@WelcomeActivity.overridePendingTransition(R.anim.no_anim, R.anim.no_anim)
        super.onStart()
    }

    override fun onPause() {
        this@WelcomeActivity.overridePendingTransition(R.anim.no_anim, R.anim.no_anim)
        super.onPause()
    }
}