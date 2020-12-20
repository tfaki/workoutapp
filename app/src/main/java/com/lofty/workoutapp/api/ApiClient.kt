package com.lofty.workoutapp.api

import com.google.gson.reflect.TypeToken
import com.lofty.workoutapp.util.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.lang.reflect.Type

object ApiClient {

    fun handleError(exception: Throwable): String{
        return when(exception){
            is HttpException -> {
                val type: Type =
                    object : TypeToken<List<String?>?>() {}.type

                val converter = retrofitService().responseBodyConverter<List<String?>?>(type, arrayOf<Annotation>())
                val errorBody = exception.response()?.errorBody() ?: return "Unknown error"

                try {
                    if (exception.code() == 404){
                        return "Unknown Error"
                    } else {
                        converter.convert(errorBody)?.first() ?: "Unknown error"
                    }
                }catch (error: IOException){
                    error.localizedMessage ?: "Unknown error"
                }
            }
            else -> {
                exception.localizedMessage ?: "Unknown Error"
            }
        }


    }

    private fun createRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(Const.URL.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    private fun retrofitService(): Retrofit {
        return createRetrofit()
    }

    val apiService: ApiService by lazy {
        retrofitService().create(ApiService::class.java)
    }
}