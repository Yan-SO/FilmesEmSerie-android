package com.example.filmesemserie.ui.screens.singup

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.filmesemserie.FilmesEmSeireApplication
import com.example.filmesemserie.data.NetworFilmesEmSeireRepository
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.filmesemserie.R
import com.example.filmesemserie.models.UserRequest
import com.example.filmesemserie.models.UserResponse
import com.example.filmesemserie.ui.navigate.RoutesName
import kotlinx.coroutines.launch


class SingUpViewModel(
private val filmesEmSeireRepository: NetworFilmesEmSeireRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(SingUpUiState())

    val uiState: State<SingUpUiState>get() = _uiState

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FilmesEmSeireApplication)
                val filmesEmSeireRepository = application.conteiner.filmesEmSeireRepository
                SingUpViewModel(filmesEmSeireRepository = filmesEmSeireRepository)
            }
        }
    }


    fun PostSingUp(navController: NavHostController, context: Context){
        _uiState.value = _uiState.value.copy(
            messageUserError = "",
            messagePasswordError = ""
        )
        viewModelScope.launch {
            if (_uiState.value.password.isNotBlank() && _uiState.value.userName.isNotBlank()){
                if (_uiState.value.password == _uiState.value.passwordConfirmation){
                    val singUpRequest= UserRequest(
                        passWord = _uiState.value.password,
                        userName = _uiState.value.userName
                    )
                    val response = filmesEmSeireRepository.postSingUp(singUpRequest)
                    Log.d("SingUpViewModel", response.toString())
                    if (response.isCorrect){
                        response as UserResponse
                        navController.navigate("${RoutesName.Home.name}/${response.id}")
                    }else{
                        _uiState.value = _uiState.value.copy(
                            messageUserError = context.getString(R.string.The_username_already_exists)
                        )
                    }

                }else{
                    _uiState.value = _uiState.value.copy(
                        messagePasswordError = context.getString(R.string.the_passwords_are_different)
                    )
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

    fun updateUserName(newUserName: String) {
        _uiState.value = _uiState.value.copy(userName = newUserName)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun updatePasswordConfirmation(passwordConfirmation: String) {
        _uiState.value = _uiState.value.copy(passwordConfirmation = passwordConfirmation)
    }


}