package com.emeraldscrolls.emeraldscrollsapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel

@Composable
fun RootHome(innerPadding: PaddingValues, notes: List<NoteModel>) {
    LazyColumn(Modifier.padding(innerPadding)) {
        items(notes){ item ->
            NoteItem(item)
            Spacer(Modifier.fillMaxWidth().background(Color.LightGray).size(1.dp))
        }
    }
}

@Composable
fun NoteItem(item: NoteModel) {
    Column(Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            fontSize = 20.sp,
            text = item.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = item.date,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            maxLines = 3,
            text = item.text,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )
    }
}