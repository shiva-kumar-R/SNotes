package com.android.snotes.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * DAO on Note for operations
 */
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>
}