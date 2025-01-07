package com.emeraldscrolls.emeraldscrollsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    fun navigateTo(route: String){
        navController.navigate(route)
    }

    EmeraldScrollsTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                viewModel.changeVisibility(showAddButton = true, showSaveButton = false)
                RootHome(state.notes, ::navigateTo)
            }
            composable("note") {
                viewModel.changeVisibility(showAddButton = false, showSaveButton = false)
                RootNote(viewModel::onSaveNote, ::onTextChange, ::onCheckClick)
            }
        }
    }
}