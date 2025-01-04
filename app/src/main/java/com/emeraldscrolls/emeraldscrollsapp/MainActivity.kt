package com.emeraldscrolls.emeraldscrollsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    var showAddButton by remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    EmeraldScrollsTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Emerald Scrolls") },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }, floatingActionButton = {
            if (showAddButton) FloatingActionButton(
                onClick = { navController.navigate("note") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new note"
                )
            }
        }) { innerPadding ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    showAddButton = true
                    RootHome(innerPadding, viewModel.notes)
                }
                composable("note") {
                    showAddButton = false
                    RootNote(navController, innerPadding, viewModel::onSaveNote)
                }
            }
        }
    }
}