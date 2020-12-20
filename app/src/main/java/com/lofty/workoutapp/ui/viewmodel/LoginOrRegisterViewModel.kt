package com.lofty.workoutapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lofty.workoutapp.api.ApiClient
import com.lofty.workoutapp.api.Resource
import com.lofty.workoutapp.api.response.LoginResponse
import com.lofty.workoutapp.api.response.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginOrRegisterViewModel() : ViewModel() {

    val registerResult = MutableLiveData<Resource<RegisterResponse>>()
    val loginResult = MutableLiveData<Resource<LoginResponse>>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun register(name: String, email: String, password: String) {
        registerResult.postValue(Resource.loading(null))
        disposable.add(
            ApiClient
                .apiService
                .register(name, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    registerResult.postValue(Resource.success(it))
                }, {
                    registerResult.postValue(Resource.error(ApiClient.handleError(it), null))
                })
        )
    }

    fun login(email: String, password: String) {
        loginResult.postValue(Resource.loading(null))
        disposable.add(
            ApiClient
                .apiService
                .login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loginResult.postValue(Resource.success(it))
                }, {
                    loginResult.postValue(Resource.error(ApiClient.handleError(it), null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}