package com.example.colorfulnotes.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.colorfulnotes.R
import com.example.colorfulnotes.util.Constants
import kotlinx.android.synthetic.main.activity_view_note.*

class ViewNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        val sharedPref = this.getSharedPreferences("com.example.colorfulnotes.file", 0)
        val color = sharedPref.getString("color", "BLUE")

        when (sharedPref.getString("color", "BLUE")) {
            Constants.COLOR_BLUE -> tvNoteView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefBlue
                )
            )
            Constants.COLOR_PURPLE -> tvNoteView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefPurple
                )
            )
            Constants.COLOR_RED -> tvNoteView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.prefRed
                )
            )
        }

        tvNoteView.text = intent.getStringExtra("text")
    }
}
