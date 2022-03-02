package com.android.snotes.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.snotes.entity.Note
import com.android.snotes.entity.NoteDatabase
import com.android.snotes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteViewModel(context: Context): ViewModel() {
    private var _allNotes = MutableLiveData<List<Note>>()
    val allNotes: LiveData<List<Note>> get() = _allNotes
    private val noteRepository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabaseInstance(context).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        _allNotes = noteRepository.allNotes as MutableLiveData<List<Note>>
    }

    fun insertNote(note: Note) = viewModelScope.launch(IO) {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(IO) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(IO) {
        noteRepository.deleteNote(note)
    }
}