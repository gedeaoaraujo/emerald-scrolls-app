package com.emeraldscrolls.emeraldscrollsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emeraldscrolls.emeraldscrollsapp.home.RootHome
import com.emeraldscrolls.emeraldscrollsapp.note.RootNote
import com.emeraldscrolls.emeraldscrollsapp.ui.theme.EmeraldScrollsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { RootApp() }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootApp() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    val state by viewModel.state.collectAsState()

    fun onCheckClick() {
        navController.popBackStack()
        viewModel.saveNewNote()
    }

    fun onTextChange(show: Boolean = false) {
        viewModel.setSaveButton(show)
    }

    EmeraldScrollsTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(text = "Emerald Scrolls")
                        if (state.showSaveButton) IconButton(onClick = { onCheckClick() }) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Save Note")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }, floatingActionButton = {
            if (state.showAddButton) FloatingActionButton(
                onClick = { navController.navigate("note") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "Add new note"
                )
            }
        }) { innerPadding ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    viewModel.changeVisibility(showAddButton = true, showSaveButton = false)
                    RootHome(innerPadding, state.notes)
                }
                composable("note") {
                    viewModel.changeVisibility(showAddButton = false, showSaveButton = false)
                    RootNote(innerPadding, viewModel::onSaveNote, ::onTextChange)
                }
            }
        }
    }
}