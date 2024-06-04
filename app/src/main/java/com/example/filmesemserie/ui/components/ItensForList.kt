package com.example.filmesemserie.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.StatusItem
import com.example.filmesemserie.models.Tipo
import com.example.filmesemserie.ui.navigate.RoutesName
import com.example.filmesemserie.ui.theme.FilmesEmSerieTheme
import com.google.gson.Gson

@Composable
fun ItenForList(item: ItemModel, navController: NavHostController, modifier: Modifier= Modifier){

    val teg= "ItenForList"
    Log.d(teg, item.toString())
    val itemModelJson = Gson().toJson(item)
    Log.d(teg, "o item depois de ser  "+item.toString())

    Card (modifier = modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {

            navController.navigate("${RoutesName.EditItem.name}/$itemModelJson/${item.tipos.name}/${item.idUser}")
        }){
        Row( horizontalArrangement = Arrangement.Center ) {

            Image(
                contentScale = ContentScale.Fit,
                painter = imageForPainter(item = item),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.5F)
            )

            Column( horizontalAlignment = Alignment.CenterHorizontally,
                 modifier= Modifier.fillMaxWidth()) {
                Text(
                    text = item.nomeItem,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Nota: "+item.nota.toString(),
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = "Status: "+item.status.toString(),
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItensListPreview(){
    FilmesEmSerieTheme {
        val item: ItemModel = ItemModel(
            id = 1,
            nomeItem = "serivaldo",
            nota = 5F,
            status = StatusItem.VENDO,
            idUser = 3,
            tipos = Tipo.DESENHO
            )
        ItenForList(item, navController = rememberNavController())
    }
}

