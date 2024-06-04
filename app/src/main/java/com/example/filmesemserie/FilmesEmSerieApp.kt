package com.example.filmesemserie


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filmesemserie.ui.navigate.FilmesEmSeriesNavHost


@Composable
fun FilmesEmSerieApp(navHostController: NavHostController= rememberNavController()){
    FilmesEmSeriesNavHost(navHostController)
}



