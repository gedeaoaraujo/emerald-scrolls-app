package com.emeraldscrolls.emeraldscrollsapp.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel

@Composable
fun RootNote(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onSaveNote: (NoteModel) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    BackHandler {
        onSaveNote(NoteModel(title, text))
        navController.popBackStack()
    }

    Column(Modifier.fillMaxWidth().padding(innerPadding)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxSize().padding(8.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }
}