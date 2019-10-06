package com.example.colorfulnotes.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorfulnotes.R
import com.example.colorfulnotes.adapter.NoteAdapter
import com.example.colorfulnotes.util.Constants
import kotlinx.android.synthetic.main.activity_note_taking.*
import kotlinx.android.synthetic.main.note_list_item.*
import java.util.*

class NoteTakingActivity : AppCompatActivity(), View.OnClickListener, NoteAdapter.NoteAdapterDelagte {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private lateinit var context: Context
    private var numNotes: Int = 0
    private var currentNote: Int = 0
    private val notes = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_taking)

        sharedPref = this.getSharedPreferences("com.example.colorfulnotes.file", 0)
        val color = sharedPref.getString("color", "BLUE")
        btnSave.setOnClickListener(this)


        numNotes = sharedPref.getInt("numNotes", 0)

        for(i in 0..numNotes){
            val note = sharedPref.getString(i.toString(), "")
            if(note != null){
                notes.add(note)
            }
        }


        when (color) {
            Constants.COLOR_BLUE -> etNote.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefBlue
                )
            )
            Constants.COLOR_RED -> etNote.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefRed
                )
            )
            Constants.COLOR_PURPLE -> etNote.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefPurple
                )
            )
        }

        updateNoteView()
        rvNotes.addItemDecoration(DividerItemDecoration(this, VERTICAL))

    }

    override fun onNoteClick(text: String){
        val intent = Intent(this, ViewNote::class.java)
        intent.putExtra("text", text)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        sharedPrefEditor = sharedPref.edit()

        if (notes.size > 7) {
            notes.removeAt(0)
        }

        notes.add(etNote.text.toString())

        if (numNotes < 7) {
            sharedPrefEditor.putInt("numNotes", numNotes)
            numNotes++
        }

        sharedPrefEditor.putString(currentNote.toString(), etNote.text.toString())
        currentNote++

        if(currentNote > 6){
            currentNote = 0
        }


        sharedPrefEditor.apply()
        etNote.setText("")
        updateNoteView()
    }

    private fun updateNoteView() {

        rvNotes.adapter = NoteAdapter(notes, this)
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

}


