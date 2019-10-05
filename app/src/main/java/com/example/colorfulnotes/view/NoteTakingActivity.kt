package com.example.colorfulnotes.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.colorfulnotes.R
import com.example.colorfulnotes.util.Constants
import kotlinx.android.synthetic.main.activity_note_taking.*

class NoteTakingActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_taking)
        sharedPref = this.getSharedPreferences("com.example.colorfulnote.preferencefile", 0)
        val color = sharedPref.getString("color", "BLUE")

        when(color){
            Constants.COLOR_BLUE -> etNote.setTextColor(ContextCompat.getColor(this, R.color.prefBlue))
            Constants.COLOR_RED -> etNote.setTextColor(ContextCompat.getColor(this, R.color.prefRed))
            Constants.COLOR_PURPLE -> etNote.setTextColor(ContextCompat.getColor(this, R.color.prefPurple))
        }
       }
}


