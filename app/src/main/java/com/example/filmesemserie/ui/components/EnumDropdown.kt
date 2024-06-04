package com.example.filmesemserie.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T : Enum<T>> EnumDropdown(
    selectedValue: T,
    onValueSelected: (T) -> Unit,
    enumValues: Array<T>,
    label: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = selectedValue.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            enumValues.forEach { value ->
                DropdownMenuItem(
                    text = { Text(value.name) },
                    onClick = {
                        onValueSelected(value)
                        expanded = false
                    }
                )
            }
        }
    }
}