package com.example.filmesemserie.ui.screens.ItensList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.Tipo
import com.example.filmesemserie.ui.components.FilmesEmSerieTopAppBar
import com.example.filmesemserie.ui.components.ItenForList
import com.example.filmesemserie.ui.navigate.RoutesName
import com.example.filmesemserie.ui.theme.ColorTheme


@Composable
fun ItemsList(
    userId:Int,
    type: Tipo,
    navController: NavHostController,
    modifier: Modifier= Modifier,
){

    val colors = ColorTheme().getColorsTheme()
    val factory = ItemsListViewModel.provideFactory(userId, type)
    val viewModel: ItemsListViewModel = viewModel(factory = factory)

    val uiState = viewModel.uiState.collectAsState().value

    val shapes = Shapes(medium = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp))


    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.findItemsForType()

            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }




    Scaffold (
        topBar = {
            FilmesEmSerieTopAppBar(
                canNavigateBack = true,
                title = type.name,
                navigateUp = { navController.navigateUp()}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colors.topBar,
                shape = shapes.medium,
                onClick = { navController.navigate("${RoutesName.NewItem.name}/${type.name}/${userId}") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = colors.black
                )

            }
        }
    ){
        ItemsListBody(
            uiState.page?.content?:mutableListOf(),
            padding = it,
            navController= navController,
            modifier = modifier
        )
        
    }
}

@Composable
fun ItemsListBody(
    listItems: List<ItemModel>,
    navController: NavHostController,
    padding: PaddingValues,
    modifier: Modifier= Modifier
){
    LazyColumn(
        Modifier
            .padding(padding)
            .padding(top = 8.dp) ,content = {
        items(items = listItems){
            ItenForList(item = it, navController = navController, modifier = modifier)
        }
    })

}
