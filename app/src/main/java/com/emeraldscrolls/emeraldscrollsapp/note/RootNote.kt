package com.emeraldscrolls.emeraldscrollsapp.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RootNote(innerPadding: PaddingValues) {
    Column(Modifier.fillMaxWidth().padding(innerPadding)) {
        Text(text = "New note")
    }
}