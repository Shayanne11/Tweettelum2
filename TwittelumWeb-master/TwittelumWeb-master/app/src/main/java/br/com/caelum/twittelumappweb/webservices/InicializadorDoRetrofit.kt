package br.com.caelum.twittelumappweb.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorDoRetrofit {

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://twittelum-server.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}