package com.example.pokemonhub.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.pokemonhub.R

@Composable
fun ConfirmDeleteDialog(
    pokemonName: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
){
    AlertDialog(
        onDismissRequest = { onCancel() },
        title = { Text(stringResource(R.string.delete_pokemon)) },
        text = { Text(stringResource(R.string.delete_pokemon_confirmation, pokemonName)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}