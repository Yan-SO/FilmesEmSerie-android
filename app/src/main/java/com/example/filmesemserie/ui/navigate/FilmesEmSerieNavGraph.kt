package com.example.filmesemserie.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.Tipo
import com.example.filmesemserie.ui.screens.ItensList.ItemsList
import com.example.filmesemserie.ui.screens.itemManipulate.ItemManipulate
import com.example.filmesemserie.ui.screens.homeScreen.HomeScreen
import com.example.filmesemserie.ui.screens.login.Login
import com.example.filmesemserie.ui.screens.singup.SingUp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun FilmesEmSeriesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = RoutesName.Login.name,
        ){

        composable(route = RoutesName.Login.name){  Login(navController= navController) }
        composable(route = RoutesName.SingUp.name){  SingUp(navController= navController) }
        composable(
            route = "${RoutesName.Home.name}/{userId}",
            arguments = listOf(navArgument("userId"){type= NavType.IntType})
        ){
            HomeScreen(navController= navController, userId= it.arguments?.getInt("userId")?:0)
        }
        composable(
            route = "${RoutesName.ItensList.name}/{userId}/{title}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val titleString = backStackEntry.arguments?.getString("title") ?: Tipo.FILME.name
            val title = Tipo.valueOf(titleString)
            ItemsList(
                userId = userId,
                type = title,
                navController = navController
            )
        }
        composable(
            route = "${RoutesName.EditItem.name}/{itemModel}/{type}/{userId}",
            arguments = listOf(
                navArgument("itemModel") { type = NavType.StringType },
                navArgument("type") { type = NavType.StringType },
                navArgument("userId"){type = NavType.IntType}
            )
        ) { backStackEntry ->
            val itemModelJson = backStackEntry.arguments?.getString("itemModel")
            val itemModel = itemModelJson?.let {
                Gson().fromJson<ItemModel>(it, object : TypeToken<ItemModel>() {}.type)
            }
            val type = backStackEntry.arguments?.getString("type")?.let {
                Tipo.valueOf(it)
            }
            val userId = backStackEntry.arguments?.getInt("userId")?:0

            if (type != null) {
                ItemManipulate(
                    navController = navController,
                    itemModel = itemModel,
                    type = type,
                    userId = userId
                )
            }
        }
        composable(
            route = "${RoutesName.NewItem.name}/{type}/{userId}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("userId"){type = NavType.IntType}
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")?.let {
                Tipo.valueOf(it)
            }
            val userId = backStackEntry.arguments?.getInt("userId")?:0

            if (type != null) {
                ItemManipulate(
                    navController = navController,
                    itemModel = null,
                    type = type,
                    userId = userId
                )
            }
        }
    }
}
