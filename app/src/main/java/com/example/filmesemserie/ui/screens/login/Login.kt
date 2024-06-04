package com.example.filmesemserie.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmesemserie.R
import com.example.filmesemserie.ui.theme.ColorTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filmesemserie.ui.navigate.RoutesName

import com.example.filmesemserie.ui.theme.FilmesEmSerieTheme


@Composable
fun Login(
    modifier: Modifier= Modifier,
    viewModel: LoginViewModel= viewModel(factory = LoginViewModel.Factory),
    navController: NavHostController
){
    val uiState by viewModel.uiState
    val context = LocalContext.current
    val colors = ColorTheme().getColorsTheme()

    Column (
        modifier =Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.app_name),
            color = colors.buttonColor,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.messageUserError.isNotBlank()){
            Text(text = uiState.messageUserError)
        }
        TextField(
            value = uiState.userName,
            onValueChange = {viewModel.updateUserName(it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = stringResource(R.string.user_name)) },
            modifier = modifier.padding(bottom = 16.dp).then(
                if (uiState.messageUserError.isNotBlank()) {
                    Modifier.border(BorderStroke(2.dp, colors.error))
                } else {
                    Modifier
                }
            )
        )

        if (uiState.messagePasswordError.isNotBlank()){
            Text(text = uiState.messagePasswordError)
        }
        TextField(
            value = uiState.password,
            onValueChange = {viewModel.updatePassword(it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = stringResource(R.string.password)) },
            modifier = modifier.padding(bottom = 16.dp).then(
                if (uiState.messagePasswordError.isNotBlank()) {
                    Modifier.border(BorderStroke(2.dp, colors.error))
                } else {
                    Modifier
                }
            )
        )

        Button(
            colors = ButtonDefaults.buttonColors(colors.buttonColor),
            border = BorderStroke(width = 2.dp, color = colors.textColor),
            onClick = {
                viewModel.getLoginResponse(navController, context = context)
                //navController.navigate(RoutesName.SingUp.name)
            },
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                style = TextStyle(color = colors.textColor, fontSize = 16.sp)
            )

        }
        Spacer(modifier = Modifier.weight(2f))
        Button(
            colors = ButtonDefaults.buttonColors(colors.buttonColor),
            border = BorderStroke(width = 2.dp, color = colors.textColor),
            onClick = { navController.navigate(RoutesName.SingUp.name) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = TextStyle(color = colors.textColor, fontSize = 16.sp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FilmesEmSerieTheme {

        Login(navController = rememberNavController())
    }
}