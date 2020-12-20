package com.lofty.workoutapp.api

import com.lofty.workoutapp.api.response.LoginResponse
import com.lofty.workoutapp.api.response.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("user/signup")
    fun register(@Query("name") name: String, @Query("email") email: String, @Query("password") password: String): Observable<RegisterResponse>

    @POST("user/login")
    fun login(@Query("email") email: String, @Query("password") password: String) : Observable<LoginResponse>
}