package com.example.filmesemserie.ui.screens.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.navigation.NavHostController
import com.example.filmesemserie.FilmesEmSeireApplication
import com.example.filmesemserie.R
import com.example.filmesemserie.data.NetworFilmesEmSeireRepository
import com.example.filmesemserie.models.UserRequest
import com.example.filmesemserie.models.MessageResponse
import com.example.filmesemserie.models.UserResponse
import com.example.filmesemserie.ui.navigate.RoutesName
import kotlinx.coroutines.launch

class LoginViewModel(private val filmesEmSeireRepository: NetworFilmesEmSeireRepository): ViewModel() {
    private val _uiState= mutableStateOf(LoginUiState())

    val uiState: State<LoginUiState> get() = _uiState


    fun getLoginResponse(navController: NavHostController, context: Context){
        _uiState.value = _uiState.value.copy(
            messageUserError = "",
            messagePasswordError = ""
        )

        viewModelScope.launch {
            if (_uiState.value.password.isNotBlank() && _uiState.value.userName.isNotBlank()){
                val loginRequest = UserRequest(
                    userName = _uiState.value.userName,
                    passWord = _uiState.value.password
                )

                val response =  filmesEmSeireRepository.getLoginUser(loginRequest)

                if (response.isCorrect){
                    response as UserResponse
                    navController.navigate("${RoutesName.Home.name}/${response.id}")
                }else{
                    val messageResponse= response as MessageResponse
                    if (messageResponse.message.equals("wrong password")){
                        _uiState.value= _uiState.value.copy(
                            messagePasswordError =  context.getString(R.string.wrong_password)
                        )
                    }else if (messageResponse.message.equals("wrong name")){
                        _uiState.value= _uiState.value.copy(
                            messageUserError = context.getString(R.string.username_not_find)
                        )
                    }else{
                        _uiState.value = _uiState.value.copy(
                            messageUserError =  context.getString(R.string.message_error_500),
                            messagePasswordError = context.getString(R.string.message_error_500)
                        )
                    }
                }
            }else{
                if (_uiState.value.password.isBlank() && _uiState.value.userName.isBlank()) {
                    _uiState.value = _uiState.value.copy(
                        messageUserError = context.getString(R.string.Enter_the_username),
                        messagePasswordError = context.getString(R.string.Enter_the_password)
                    )
                } else if(_uiState.value.password.isBlank()){
                    _uiState.value = _uiState.value.copy(
                        messagePasswordError = context.getString(R.string.Enter_the_password)
                    )
                }else {
                    _uiState.value = _uiState.value.copy(
                        messageUserError = context.getString(R.string.Enter_the_username)
                    )
                }
            }

        }
    }



    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmesEmSeireApplication)
                val filmesEmSeireRepository = application.conteiner.filmesEmSeireRepository
                LoginViewModel(filmesEmSeireRepository = filmesEmSeireRepository)
            }
        }
    }


    /*
    * set userName
    * */
    fun updateUserName(userName:String){
        _uiState.value = _uiState.value.copy(userName = userName)
    }

    /*
    * set password
    * */
    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }
}