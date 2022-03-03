package com.android.snotes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.snotes.entity.Note
import com.android.snotes.entity.NoteDao
import com.android.snotes.entity.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteRepository {

    companion object {
        var noteDatabase: NoteDatabase? = null
        private var noteDao: NoteDao? = null
        private var allNotes: Flow<List<Note>>? = null

        fun initializeDB(context: Context) {
            noteDatabase = NoteDatabase.getDatabaseInstance(context)
            noteDao = noteDatabase!!.getNoteDao()
            allNotes = noteDao!!.getAllNotes()
        }

        //Access all notes from here
        fun getAllNotes(context: Context): Flow<List<Note>>? {
            initializeDB(context)
            return allNotes
        }

        //Insert note
        fun insertNote(context: Context, note: Note) {
            initializeDB(context)
            CoroutineScope(IO).launch {
                noteDao?.insertNote(note)
            }
        }

        //Update note
        fun updateNote(context: Context, note: Note) {
            initializeDB(context)
            CoroutineScope(IO).launch {
                noteDao?.updateNote(note)
            }
        }

        //Delete note
        fun deleteNote(context: Context, note: Note) {
            initializeDB(context)
            CoroutineScope(IO).launch {
                noteDao?.deleteNote(note)
            }
        }
    }
}