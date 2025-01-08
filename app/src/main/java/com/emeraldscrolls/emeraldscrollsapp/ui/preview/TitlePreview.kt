package com.emeraldscrolls.emeraldscrollsapp.ui.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel

@Composable
fun TitlePreview(
    scroll: ScrollModel?,
    onDeleteScroll: (Int) -> Unit,
    onEditScroll: (ScrollModel) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Preview Scroll",
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row {
            IconButton(onClick = {
                scroll?.let { onEditScroll(it) }
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Scroll"
                )
            }
            IconButton(onClick = {
                scroll?.id?.let { onDeleteScroll(it) }
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Scroll"
                )
            }
        }
    }
}