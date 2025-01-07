package com.emeraldscrolls.emeraldscrollsapp

import androidx.lifecycle.ViewModel
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.repository.ScrollRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class ScrollState(
    val scrolls: List<ScrollModel> = emptyList(),
    val newScroll: ScrollModel = ScrollModel(),
    val selectedScroll: ScrollModel? = null
)

class MainViewModel(
    private val scrollRepository: ScrollRepository
): ViewModel() {

    val state = MutableStateFlow(ScrollState())

    fun saveNewScroll() {
        val list = state.value.scrolls.toMutableList()
        list.add(state.value.newScroll)
        state.update { it.copy(newScroll = ScrollModel()) }
        state.update { it.copy(scrolls = list) }
    }

    fun onSaveScroll(scroll: ScrollModel){
        state.update { it.copy(newScroll = scroll) }
    }

    fun setSelectedItem(item: ScrollModel) {
        state.update { it.copy(selectedScroll = item) }
    }

}