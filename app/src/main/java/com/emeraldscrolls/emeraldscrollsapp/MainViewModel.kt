package com.emeraldscrolls.emeraldscrollsapp

import androidx.lifecycle.ViewModel
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

data class NoteState(
    val notes: List<NoteModel> = emptyList(),
    val newNote: NoteModel = NoteModel(),
    val selectedNote: NoteModel? = null
)

class MainViewModel: ViewModel() {

    val state = MutableStateFlow(NoteState())

    fun saveNewNote() {
        val list = state.value.notes.toMutableList()
        list.add(state.value.newNote)
        state.update { it.copy(newNote = NoteModel()) }
        state.update { it.copy(notes = list) }
    }

    fun onSaveNote(note: NoteModel){
        state.update { it.copy(newNote = note) }
    }

    fun setSelectedItem(item: NoteModel) {
        state.update { it.copy(selectedNote = item) }
    }

}