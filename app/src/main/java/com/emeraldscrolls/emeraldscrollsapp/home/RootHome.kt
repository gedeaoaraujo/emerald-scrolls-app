package com.emeraldscrolls.emeraldscrollsapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel

@Composable
fun RootHome(innerPadding: PaddingValues, notes: List<NoteModel>) {
    LazyColumn(Modifier.padding(innerPadding)) {
        items(notes){ item ->
            NoteItem(item)
        }
    }
}

@Composable
fun NoteItem(item: NoteModel) {
    Column(Modifier.padding(8.dp)) {
        Text(text = item.title)
        Text(text = item.text)
    }
}