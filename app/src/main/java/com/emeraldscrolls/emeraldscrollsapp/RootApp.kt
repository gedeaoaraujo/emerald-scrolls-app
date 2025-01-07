package com.emeraldscrolls.emeraldscrollsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.ui.Routes
import com.emeraldscrolls.emeraldscrollsapp.ui.home.RootHome
import com.emeraldscrolls.emeraldscrollsapp.ui.scroll.RootScroll
import com.emeraldscrolls.emeraldscrollsapp.ui.preview.RootPreview
import com.emeraldscrolls.emeraldscrollsapp.ui.theme.EmeraldScrollsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootApp() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()

    fun onCheckClick() {
        navController.popBackStack()
        viewModel.saveNewScroll()
    }

    fun navigateTo(route: String){
        navController.navigate(route)
    }

    fun onSelectItem(item: ScrollModel){
        viewModel.setSelectedItem(item)
        navController.navigate(Routes.PREVIEW)
    }

    EmeraldScrollsTheme {
        NavHost(navController = navController, startDestination = Routes.HOME) {
            composable(Routes.HOME) {
                RootHome(state.scrolls, ::navigateTo, ::onSelectItem)
            }
            composable(Routes.SCROLL) {
                RootScroll(viewModel::onSaveScroll, ::onCheckClick)
            }
            composable(Routes.PREVIEW) {
                RootPreview(state.selectedScroll)
            }
        }
    }
}