package com.example.filmesemserie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


class ColorTheme(){

    @Composable
    fun getColorsTheme(): Colors{
        return if (isSystemInDarkTheme()){
            Colors(
                buttonColor =Color(0xFFFF4214),
                textColor = Color(0xFFCCCCCC),
                topBar = Color(0xFFFF4214),
                cancelButtton = Color(0xFFFF0000)
            )
        }else{
            Colors(
                buttonColor = Color(19, 124, 206, 255),
                textColor = Color(197, 197, 197, 255),
                topBar = Color(19, 124, 206, 255),
                cancelButtton = Color(0xFFFF0000)
            )

        }
    }

}

data class Colors(
    val buttonColor: Color,
    val textColor: Color,
    val topBar: Color,
    val cancelButtton: Color,
    val black: Color = Color(0, 0, 0, 255),
    val error: Color= Color(255, 0, 0, 255)
)