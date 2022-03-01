package com.android.snotes.repository

import androidx.lifecycle.LiveData
import com.android.snotes.entity.Note
import com.android.snotes.entity.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    //Access all notes from here
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    //Insert note
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    //Update note
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    //Delete note
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}