package com.android.snotes.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity note_table with its fields
 */
@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "description")
    val noteDescription: String,
    @ColumnInfo(name = "timeStamp")
    val noteTimestamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
