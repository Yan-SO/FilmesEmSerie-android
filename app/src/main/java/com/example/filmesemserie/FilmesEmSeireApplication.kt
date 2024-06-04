package com.example.filmesemserie

import android.app.Application
import com.example.filmesemserie.data.AppContainer
import com.example.filmesemserie.data.DefaultAppContainer

class FilmesEmSeireApplication: Application(){

    lateinit var conteiner: AppContainer
    override fun onCreate() {
        super.onCreate()
        conteiner = DefaultAppContainer()
    }

}