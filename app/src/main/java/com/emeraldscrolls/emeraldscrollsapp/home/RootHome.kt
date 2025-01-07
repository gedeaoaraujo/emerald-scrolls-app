package com.emeraldscrolls.emeraldscrollsapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootHome(
    notes: List<NoteModel>,
    navigateTo: (String) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Emerald Scrolls")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navigateTo("note") },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "Add new note"
            )
        }
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            if (notes.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 24.sp,
                        text = "The Library is empty",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.size(16.dp))
                    Text(
                        fontSize = 18.sp,
                        text = "Write a new scroll to this library"
                    )
                }
            } else {
                LazyColumn {
                    items(notes){ item ->
                        NoteItem(item)
                        Spacer(Modifier.fillMaxWidth()
                            .background(Color.LightGray).size(1.dp))
                    }
                }
            }
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
