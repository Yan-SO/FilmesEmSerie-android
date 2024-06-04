package com.example.filmesemserie.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.filmesemserie.R
import kotlinx.coroutines.delay

@Composable
fun ShowPopup(message: String, onClose: ()->Unit) {
    var showDialog by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)  // Wait for 2 seconds
        showDialog = false
        onClose()
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false; onClose()},
            confirmButton = {
                TextButton(onClick = { showDialog = false; onClose() }) {
                    Text(stringResource(R.string.ok))
                }
            },
            text = {
                Text(message)
            }
        )
    }
}