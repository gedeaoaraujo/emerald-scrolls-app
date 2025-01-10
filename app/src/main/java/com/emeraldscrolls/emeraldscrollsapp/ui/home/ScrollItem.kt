package com.emeraldscrolls.emeraldscrollsapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt

@Composable
fun ScrollItem(item: ScrollModel, onSelectItem: () -> Unit) {
    Column(Modifier.fillMaxWidth().padding(8.dp).clickable { onSelectItem() }) {
        Text(
            text = item.title,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            color = Color.Gray,
            text = item.date?.dateTimeFmt().orEmpty(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            maxLines = 3,
            text = item.text,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
