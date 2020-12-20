package com.lofty.workoutapp.ui.activity

import androidx.lifecycle.Observer
import com.jaychang.st.SimpleText
import com.lofty.workoutapp.MainActivity
import com.lofty.workoutapp.R
import com.lofty.workoutapp.core.BaseAlphaActivity
import com.lofty.workoutapp.databinding.ActivityLoginOrRegisterBinding
import com.lofty.workoutapp.helper.*
import com.lofty.workoutapp.ui.viewmodel.LoginOrRegisterViewModel
import com.lofty.workoutapp.util.Const

class LoginActivity : BaseAlphaActivity<ActivityLoginOrRegisterBinding, LoginOrRegisterViewModel>() {

    private lateinit var screenType: String

    override fun getViewBinding(): ActivityLoginOrRegisterBinding = ActivityLoginOrRegisterBinding.inflate(layoutInflater)

    override fun getViewModelClass(): Class<LoginOrRegisterViewModel> = LoginOrRegisterViewModel::class.java

    override fun bindScreen() {
        parseIntent()
        arrangeUI()
        arrangeListener()
        arrangeObservers()
    }

    private fun arrangeListener() {
        setOnClickListeners(listOf(binding.textViewAlreadyHasAccount)) {
            binding.editTextName.setText("")
            binding.editTextEmail.setText("")
            binding.editTextPassword.setText("")
            if (screenType == ScreenType.LOGIN_TYPE.value) {
                screenType = ScreenType.REGISTER_TYPE.value
            } else {
                screenType = ScreenType.LOGIN_TYPE.value
            }
            arrangeUI()
        }

        setOnClickListeners(listOf(binding.imageViewDone)) {
            if (screenType == ScreenType.REGISTER_TYPE.value) {
                if (checkFields()){
                    val name = binding.editTextName.text.toString()
                    val email = binding.editTextEmail.text.toString()
                    val password = binding.editTextPassword.text.toString()
                    viewModel.register(name, email, password)
                } else {
                    showAlertDialog("Girdiğiniz değerleri kontrol ediniz.")
                }
            } else {
                if (checkFields()) {
                    val email = binding.editTextEmail.text.toString()
                    val password = binding.editTextPassword.text.toString()
                    viewModel.login(email, password)
                } else {
                    showAlertDialog("Girdiğiniz değerleri kontrol ediniz.")
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        //TODO: checkbox kontrol
        if (screenType == ScreenType.REGISTER_TYPE.value){
            return !binding.editTextName.text.isNullOrEmpty() && !binding.editTextEmail.text.isNullOrEmpty() && !binding.editTextPassword.text.isNullOrEmpty()
        }else {
            return !binding.editTextEmail.text.isNullOrEmpty() && !binding.editTextPassword.text.isNullOrEmpty()
        }
    }

    private fun arrangeObservers(){
        viewModel.registerResult.observe(this, Observer {
            handleResult(it, onSuccess = {
                toast(it?.message!!.user_id)
            }, onError = {
                toast(it!!)
            })
        })
        viewModel.loginResult.observe(this, Observer {
            handleResult(it, onSuccess = {
               startActivity<MainActivity>()
            }, onError = {
                toast(it!!)
            })
        })
    }

    private fun arrangeUI() {
        if (screenType == ScreenType.LOGIN_TYPE.value) {
            binding.tilName.hide()
            binding.textViewForgetPassword.show()
            binding.textViewAgreement.hide()
            binding.agreement.hide()
            binding.textViewAlreadyHasAccount.text = "Hesabınız var mı?"
            binding.textViewTitle.text = "Giriş yap"
        } else {
            binding.tilName.show()
            binding.textViewForgetPassword.hide()
            binding.textViewAgreement.show()
            binding.agreement.show()
            binding.textViewAlreadyHasAccount.text = "Henüz üye değil misiniz?"
            binding.textViewTitle.text = "kayıt ol"
            initAgreement()
        }
    }

    private fun parseIntent() {
        screenType = intent.extras?.getString(Const.BundleName.SCREEN_TYPE)!!
    }

    private fun initAgreement() {
        val simpleText = SimpleText.from(getString(R.string.agreement))
                .first(getString(R.string.agreement_part_first))
                .textColor(R.color.colorPrettyOrange)
                .pressedTextColor(R.color.colorPrettyOrange)
                .onClick(binding.textViewAgreement) { _, _, _ ->

                }
                .first(getString(R.string.agreement_part_second))
                .textColor(R.color.colorPrettyOrange)
                .pressedTextColor(R.color.colorPrettyOrange)
                .onClick(binding.textViewAgreement) { _, _, _ ->

                }
                .first(getString(R.string.agreement_part_third))
                .textColor(R.color.colorPrettyPurple)
                .pressedTextColor(R.color.colorPrettyPurple)
                .onClick(binding.textViewAgreement) { _, _, _ ->

                }
                .first(getString(R.string.agreement_part_fourth))
                .textColor(R.color.colorPrettyOrange)
                .pressedTextColor(R.color.colorPrettyOrange)
                .onClick(binding.textViewAgreement) { _, _, _ ->

                }

        binding.textViewAgreement.text = simpleText
    }
}

enum class ScreenType(val value: String) {
    LOGIN_TYPE("login-type"),
    REGISTER_TYPE("register-type")
}