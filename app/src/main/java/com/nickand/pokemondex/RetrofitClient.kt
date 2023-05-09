package com.nickand.pokemondex

import retrofit2.Retrofit
import com.nickand.pokemondex.RetrofitClient
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String?): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}