package com.example.filmesemserie.ui.screens.singup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.filmesemserie.ui.theme.Colors
import com.example.filmesemserie.ui.theme.FilmesEmSerieTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.filmesemserie.ui.components.FilmesEmSerieTopAppBar
import com.example.filmesemserie.ui.navigate.RoutesName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUp(
    modifier: Modifier=Modifier,
    viewModel: SingUpViewModel= viewModel(factory = SingUpViewModel.Factory),
    navController: NavHostController
){

    val context = LocalContext.current
    val uiState by viewModel.uiState
    val colors = ColorTheme().getColorsTheme()

    Scaffold(
        topBar = {
            FilmesEmSerieTopAppBar(
                canNavigateBack = true,
                title = stringResource(R.string.sign_up_title),
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = Modifier
        ) {padding ->
        SignUpBody(
            userName = uiState.userName,
            onUserNameChange = { viewModel.updateUserName(it) },
            password = uiState.password,
            onPasswordChange = { viewModel.updatePassword(it) },
            passwordConfirmation = uiState.passwordConfirmation,
            onPasswordConfirmationChange = { viewModel.updatePasswordConfirmation(it) },
            colors = colors,
            onCreatedButton =  { viewModel.PostSingUp(navController, context = context) },
            uiState = uiState,
            padding = padding
        )
    }
}

@Composable
fun SignUpBody(
    modifier: Modifier = Modifier,
    userName: String,
    onUserNameChange: (String)-> Unit,
    password: String,
    onPasswordChange: (String) ->Unit,
    passwordConfirmation:String,
    onPasswordConfirmationChange: (String) -> Unit,
    colors: Colors,
    padding:PaddingValues,
    uiState: SingUpUiState,
    onCreatedButton: ()->Unit ,
    ){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.app_name),
            color = colors.buttonColor,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.sing_up),
            color = colors.buttonColor,
            style = TextStyle(
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.messageUserError.isNotBlank()){
            Text(text = uiState.messageUserError)
        }
        TextField(
            value = userName,
            onValueChange =onUserNameChange,
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
            value = password,
            onValueChange =onPasswordChange,
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
        if (uiState.messagePasswordError.isNotBlank()){
            Text(text = uiState.messagePasswordError)
        }
        TextField(
            value = passwordConfirmation,
            onValueChange =onPasswordConfirmationChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = stringResource(R.string.password_confirmation)) },
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
            onClick =  onCreatedButton,
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
fun SingUpPreview() {
    FilmesEmSerieTheme {
        SingUp(navController = rememberNavController())
    }
}
