package com.android.snotes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.snotes.entity.Note
import com.android.snotes.util.Util
import com.android.snotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_add_note.note_description_textInputEditText
import kotlinx.android.synthetic.main.activity_add_note.note_save
import kotlinx.android.synthetic.main.activity_add_note.note_title_textInputEditText
import java.text.SimpleDateFormat
import java.util.*

class EditNoteActvity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private var noteId: Int = -1
    private var notePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setSupportActionBar(notes_toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_arrow_left, theme))
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setTitle(R.string.update_note_title)
        }

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        noteId = intent.getIntExtra("noteId", -1)
        notePosition = intent.getIntExtra("notePosition", -1)

        noteViewModel.getAllNotes()?.observe(this, {
            it?.let {
                if (it.get(notePosition)?.id == noteId) {
                    val note = it.get(notePosition)
                    if (note == null) return@let
                    else {
                        note_title_textInputEditText.setText(note.noteTitle)
                        note_description_textInputEditText.setText(note.noteDescription)
                    }
                }
            }
        })

        note_save.setOnClickListener {
            val noteTitle = note_title_textInputEditText.text.toString()
            val noteDescription = note_description_textInputEditText.text.toString()

            if (noteTitle.isNullOrEmpty() || noteTitle.isNullOrBlank() || noteDescription.isNullOrEmpty() || noteDescription.isNullOrBlank())
                Util.showMessage(
                    this?.window.decorView.rootView,
                    "Note is empty. Discarded successfully"
                )
            else {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDate = sdf.format(Date())
                val updateNote = Note(noteTitle, noteDescription, currentDate)
                updateNote.id = noteId
                noteViewModel.updateNote(this, updateNote)
                Util.showMessage(this?.window.decorView.rootView, "Note updated successfully")
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}