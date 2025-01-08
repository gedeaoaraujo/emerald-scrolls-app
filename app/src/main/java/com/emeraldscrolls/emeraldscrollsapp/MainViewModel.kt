package com.emeraldscrolls.emeraldscrollsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.repository.ScrollRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ScrollState(
    val scrolls: List<ScrollModel> = emptyList(),
    val newScroll: ScrollModel = ScrollModel(),
    val selectedScroll: ScrollModel? = null
)

class MainViewModel(
    private val scrollRepository: ScrollRepository
): ViewModel() {

    private val _scrolls = scrollRepository.getAll()

    private val _state = MutableStateFlow(ScrollState())
    val state: StateFlow<ScrollState> = _state
        .onStart {
            _scrolls.onEach { scrolls ->
                _state.update { it.copy(scrolls = scrolls) }
            }.stateIn(viewModelScope)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ScrollState()
        )

    fun saveNewScroll() = viewModelScope.launch(Dispatchers.IO) {
        scrollRepository.insert(state.value.newScroll)
    }

    fun onSaveScroll(scroll: ScrollModel){
        _state.update { it.copy(newScroll = scroll) }
    }

    fun setSelectedItem(item: ScrollModel) {
        _state.update { it.copy(selectedScroll = item) }
    }

    fun deleteScroll(itemId: Int) = viewModelScope.launch(Dispatchers.IO) {
        scrollRepository.deleteById(itemId)
    }

}