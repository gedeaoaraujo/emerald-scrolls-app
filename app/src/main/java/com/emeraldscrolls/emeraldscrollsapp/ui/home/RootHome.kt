package com.emeraldscrolls.emeraldscrollsapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.ui.Routes

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootHome(
    scrolls: List<ScrollModel>,
    navigateTo: (String) -> Unit,
    onSelectItem: (ScrollModel) -> Unit
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
                    Text(
                        text = "Emerald Scrolls",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navigateTo(Routes.SCROLL) },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "Add new scroll"
            )
        }
    }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            if (scrolls.isEmpty()){
                EmptyScrollList()
            } else {
                LazyColumn {
                    items(scrolls){ item ->
                        ScrollItem(item) { onSelectItem(item) }
                        Spacer(Modifier.fillMaxWidth()
                            .background(Color.LightGray).size(1.dp))
                    }
                }
            }
        }
    }
}