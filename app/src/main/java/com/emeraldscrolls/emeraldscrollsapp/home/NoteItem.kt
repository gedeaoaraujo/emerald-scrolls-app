package com.emeraldscrolls.emeraldscrollsapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt

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
            fontSize = 16.sp,
            color = Color.Gray,
            text = item.date?.dateTimeFmt().orEmpty(),
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
