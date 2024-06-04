package com.example.filmesemserie.ui.screens.singup

data class SingUpUiState(
    var userName: String="",
    var password: String="",
    var passwordConfirmation: String="",
    var messageUserError:String="",
    var messagePasswordError:String=""
)
