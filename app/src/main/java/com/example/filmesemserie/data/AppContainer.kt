package com.example.filmesemserie.data

import com.example.filmesemserie.network.FilmesEmSeireApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer {
    val filmesEmSeireRepository: NetworFilmesEmSeireRepository
}



class DefaultAppContainer ():AppContainer{

    //TODO "coloque o URL base"
    private val baseUrl= "URL base aqui "


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: FilmesEmSeireApiService by lazy {
        retrofit.create(FilmesEmSeireApiService::class.java)
    }

    override val filmesEmSeireRepository: NetworFilmesEmSeireRepository by lazy {
        NetworFilmesEmSeireRepository(retrofitService)
    }


}