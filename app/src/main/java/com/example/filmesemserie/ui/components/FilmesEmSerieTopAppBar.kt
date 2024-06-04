package com.example.filmesemserie.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.filmesemserie.ui.theme.ColorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmesEmSerieTopAppBar(
    canNavigateBack: Boolean,
    title: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = ColorTheme().getColorsTheme()

    TopAppBar(
        title = { Text(title, style = TextStyle(fontSize = 21.sp, color = colors.textColor)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colors.topBar
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}