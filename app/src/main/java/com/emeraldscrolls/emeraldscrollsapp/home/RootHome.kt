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

private val notes = listOf(
    "Title 1" to "lkajsdkljsaldjalsjdlçajsdlkjasldkj",
    "Title 1" to "lkajsdkljsaldjalsjdlçajsdlkjasldkj",
    "Title 1" to "lkajsdkljsaldjalsjdlçajsdlkjasldkj",
)

@Composable
fun RootHome(innerPadding: PaddingValues) {
    LazyColumn(Modifier.padding(innerPadding)) {
        items(notes){ item ->
            NoteItem(item)
        }
    }
}

@Composable
fun NoteItem(item: Pair<String, String>) {
    Column(Modifier.padding(8.dp)) {
        Text(text = item.component1())
        Text(text = item.component2())
    }
}