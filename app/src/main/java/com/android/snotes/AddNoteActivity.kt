package com.android.snotes

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.android.snotes.entity.Note
import com.android.snotes.util.Util
import com.android.snotes.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setSupportActionBar(notes_toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_arrow_left, theme))
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setTitle(R.string.add_note_title)
        }

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        note_save.setOnClickListener {
            val noteTitle = note_title_textInputEditText.text.toString()
            val noteDescription = note_description_textInputEditText.text.toString()

            if (noteTitle.isNullOrEmpty() || noteTitle.isNullOrBlank() || noteDescription.isNullOrEmpty() || noteDescription.isNullOrBlank())
                Util.showToastMessage(
                    this,
                    "Note is empty. Discarded successfully"
                )
            else {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDate = sdf.format(Date())
                val addNote = Note(noteTitle, noteDescription, currentDate)
                noteViewModel.insertNote(this@AddNoteActivity, addNote)
                Util.showToastMessage(this, "Note added successfully")
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }

}