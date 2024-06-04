package com.example.filmesemserie.ui.screens.itemManipulate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmesemserie.R
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.StatusItem
import com.example.filmesemserie.models.Tipo
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.filmesemserie.ui.components.EnumDropdown
import com.example.filmesemserie.ui.components.FilmesEmSerieTopAppBar
import com.example.filmesemserie.ui.components.ShowPopup
import com.example.filmesemserie.ui.theme.ColorTheme


@Composable
fun ItemManipulate(
        modifier: Modifier= Modifier,
        navController: NavHostController,
        itemModel: ItemModel?,
        type: Tipo,
        userId:Int
){
    val factory = EditItemViewModel.provideFactory(item = itemModel, type = type)
    val viewModel:EditItemViewModel=  viewModel(factory = factory)
    val uiState by viewModel.uiState
    val colors= ColorTheme().getColorsTheme()


    if (viewModel.uiState.value.showPopup) {
        ShowPopup(
            message = if (itemModel==null) {
                stringResource(R.string.item_created_with_success)
            }else{
                stringResource(R.string.item_updated_successfully)
                 },
            onClose = {
                viewModel.showPopupFalse();
                navController.navigateUp()
            }
        )
    }
    Scaffold (
        topBar = {
            FilmesEmSerieTopAppBar(
                canNavigateBack = true,
                title = if(itemModel == null){
                    "Adicionar novo ${uiState.type.toString().lowercase()}"
                }else{
                    "Editar ${uiState.type.toString().lowercase()}"
                },
                navigateUp = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("itemSaved", false)
                    navController.navigateUp()
                }
            )
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(it)
                .padding(top = 8.dp))
        {
            if (uiState.isNullName){
                Text(text = stringResource(R.string.enter_name, type.name.lowercase()))
            }

            TextField(
                value = uiState.nome,
                onValueChange = {viewModel.updateItemName(it)},
                keyboardOptions = KeyboardOptions
                    (autoCorrect = false, keyboardType = KeyboardType.Text),
                label = { Text(text = "Nome do ${uiState.type.toString().lowercase()}")},
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .then(
                        if (uiState.isNullName) {
                            Modifier.border(BorderStroke(2.dp, colors.error))
                        } else {
                            Modifier
                        }
                    )
            )
            if (uiState.isNullNota){
                Text(text = stringResource(R.string.enter_score))
            }
            TextField(
                value = uiState.nota,
                onValueChange = { viewModel.updateNota(it) },
                keyboardOptions = KeyboardOptions
                    (autoCorrect = false, keyboardType = KeyboardType.Number),
                label = { Text(text = "Nota do ${uiState.type.toString().lowercase()}")},
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .then(
                        if (uiState.isNullNota) {
                            Modifier.border(BorderStroke(2.dp, colors.error))
                        } else {
                            Modifier
                        }
                    )
            )
            EnumDropdown(
                selectedValue = uiState.state,
                onValueSelected = { viewModel.updateStateItem(it) },
                enumValues = StatusItem.values(),
                label ="Estatos da ${uiState.type.toString().lowercase()}",
                modifier =  Modifier.padding(4.dp)
            )

            Card (modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(R.drawable.item_sem_imagem),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .clickable {
                            /*
                            * desenvouver uma fun pegarImagem()
                            *
                            * */
                        }
                )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.7F))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if(itemModel!=null){
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.cancelButtton),
                        onClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set("itemSaved", false)
                            navController.navigateUp()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Button(
                    onClick = {
                        if(viewModel.checkNull()){
                            if (itemModel==null){
                                viewModel.postItem(userId)
                            }else{
                                viewModel.updateItemPut(itemModel.id)

                            }

                        }
                    }
                ) {
                    Text(
                        text = if(itemModel==null){
                            stringResource(R.string.to_create)
                        }else{
                            stringResource(R.string.to_save)
                        }
                        ,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

            }
        }
    }
}

