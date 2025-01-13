package com.emeraldscrolls.emeraldscrollsapp.ui.preview

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.R
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.utils.PdfCreator
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt

@Composable
fun TitlePreview(
    scroll: ScrollModel?,
    onDeleteScroll: (Int) -> Unit,
    onEditScroll: (ScrollModel) -> Unit
) {
    val context = LocalContext.current
    fun onShareScroll(item: ScrollModel){
        val date = item.date
            ?.dateTimeFmt().orEmpty()
        val str = buildString {
            appendLine(item.title)
            appendLine(date)
            appendLine(item.text)
        }
        val content = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, str)
            type = "text/plain"
        }
        val startIntent = Intent.createChooser(content, null)
        context.startActivity(startIntent)
    }

    fun onGenerateFile(){
        val isGenerated = PdfCreator.generatePDF(context, scroll)
        val text = if (isGenerated) "Pdf file is generated"
        else "Pdf file could not be generated"
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Preview Scroll",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )
        Row {
            IconButton(onClick = {
                scroll?.let { onGenerateFile() }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_pdf),
                    contentDescription = "Print Scroll"
                )
            }
            IconButton(onClick = {
                scroll?.let { onShareScroll(it) }
            }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share Scroll"
                )
            }
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