package com.android.snotes.viewmodel

import android.app.Application
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

class NoteViewModel : ViewModel() {
    private var allNotes: LiveData<List<Note>>? = null

    fun getAllNotes(): LiveData<List<Note>>?{
        allNotes = NoteRepository.getAllNotes()
        return allNotes
    }

    fun insertNote(context: Context, note: Note) {
        NoteRepository.insertNote(context, note)
    }

    fun updateNote(context: Context, note: Note) {
        NoteRepository.updateNote(context, note)
    }

    fun deleteNote(context: Context, note: Note) {
        NoteRepository.deleteNote(context, note)
    }
}