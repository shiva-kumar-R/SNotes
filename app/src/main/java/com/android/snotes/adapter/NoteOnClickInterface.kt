package com.android.snotes.adapter

import com.android.snotes.entity.Note

interface NoteOnClickInterface {
    fun onNoteClick(note: Note, position: Int)
    fun onDelete(note: Note)
}