package com.example.filmesemserie.ui.screens.homeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filmesemserie.R
import com.example.filmesemserie.models.Tipo
import com.example.filmesemserie.ui.components.FilmesEmSerieTopAppBar
import com.example.filmesemserie.ui.navigate.RoutesName
import com.example.filmesemserie.ui.theme.ColorTheme
import com.example.filmesemserie.ui.theme.FilmesEmSerieTheme


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController, userId: Int){


    Scaffold (
        topBar = {
            FilmesEmSerieTopAppBar(
                canNavigateBack = false,
                title = stringResource(R.string.home_title),
                navigateUp = { /*TODO*/ }
            )
        }
    ){
        HomeBody(
            navController = navController,
            padding = it,
            modifier = modifier,
            userId= userId
        )
    }
}

@Composable
fun HomeBody(
    modifier: Modifier= Modifier,
    navController: NavHostController,
    padding: PaddingValues,
    userId: Int
){
    Log.d("HomeScreen", "$userId")
    val scroll= rememberScrollState()

    Column (modifier = modifier
        .padding(padding)
        .verticalScroll(scroll)) {

        Card (Modifier.padding(4.dp)) {
            Image(
                painter = painterResource(R.drawable.desenhos),
                contentDescription = stringResource(R.string.cartoons),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                        "${RoutesName.ItensList.name}/$userId/${Tipo.DESENHO.name}")
                    }
                    .fillMaxWidth()
            )
        }
        Card(Modifier.padding(4.dp)) {
            Image(
                painter = painterResource(R.drawable.filmes),
                contentDescription = stringResource(R.string.movies),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            "${RoutesName.ItensList.name}/$userId/${Tipo.FILME.name}")
                    }
                    .fillMaxWidth()
            )
        }
        Card (Modifier.padding(4.dp)){
            Image(
                painter = painterResource(R.drawable.animes),
                contentDescription = stringResource(R.string.animes),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                        "${RoutesName.ItensList.name}/$userId/${Tipo.ANIME.name}")
                    }
                    .fillMaxWidth()
            )
        }
        Card (Modifier.padding(4.dp)) {
            Image(
                painter = painterResource(R.drawable.series),
                contentDescription = stringResource(R.string.series),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            "${RoutesName.ItensList.name}/$userId/${Tipo.SERIE.name}")
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreviw(){
    FilmesEmSerieTheme {
        HomeScreen(navController = rememberNavController(), userId = 1)
    }
}