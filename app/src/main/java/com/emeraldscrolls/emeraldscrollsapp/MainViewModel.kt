package com.emeraldscrolls.emeraldscrollsapp

import androidx.lifecycle.ViewModel
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NoteState(
    val showAddButton: Boolean = true,
    val showSaveButton: Boolean = false,
    val notes: List<NoteModel> = emptyList(),
    val newNote: NoteModel = NoteModel()
)

class MainViewModel: ViewModel() {

    val state = MutableStateFlow(NoteState())

    fun changeVisibility(showAddButton: Boolean, showSaveButton: Boolean) {
        state.update { it.copy(showAddButton = showAddButton, showSaveButton = showSaveButton) }
    }

    fun saveNewNote() {
        val list = state.value.notes.toMutableList()
        list.add(state.value.newNote.copy(date = dateTimeFmt()))
        state.update { it.copy(newNote = NoteModel()) }
        state.update { it.copy(notes = list) }
    }

    fun changeTitle(title: String){
        state.update { it.copy(newNote = it.newNote.copy(title = title)) }
    }

    fun changeText(text: String){
        state.update { it.copy(newNote = it.newNote.copy(text = text)) }
    }

}