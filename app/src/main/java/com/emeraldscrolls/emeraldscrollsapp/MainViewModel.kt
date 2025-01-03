package com.emeraldscrolls.emeraldscrollsapp

import androidx.lifecycle.ViewModel
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel

class MainViewModel: ViewModel() {

    val notes = mutableListOf<NoteModel>()

    fun onSaveNote(item: NoteModel){
        notes.add(item)
    }

}