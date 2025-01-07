package com.emeraldscrolls.emeraldscrollsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import com.emeraldscrolls.emeraldscrollsapp.ui.home.RootHome
import com.emeraldscrolls.emeraldscrollsapp.ui.note.RootNote
import com.emeraldscrolls.emeraldscrollsapp.ui.preview.RootPreview
import com.emeraldscrolls.emeraldscrollsapp.ui.theme.EmeraldScrollsTheme

@Composable
fun RootApp() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    val state by viewModel.state.collectAsState()

    fun onCheckClick() {
        navController.popBackStack()
        viewModel.saveNewNote()
    }

    fun navigateTo(route: String){
        navController.navigate(route)
    }

    fun onSelectItem(item: NoteModel){
        viewModel.setSelectedItem(item)
        navController.navigate("preview")
    }

    EmeraldScrollsTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                RootHome(state.notes, ::navigateTo, ::onSelectItem)
            }
            composable("note") {
                RootNote(viewModel::onSaveNote, ::onCheckClick)
            }
            composable("preview") {
                RootPreview(state.selectedNote)
            }
        }
    }
}