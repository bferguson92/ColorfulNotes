package com.example.colorfulnotes.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorfulnotes.R
import com.example.colorfulnotes.adapter.ColorAdapter
import com.example.colorfulnotes.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ColorAdapter.ColorAdapterDelegate {


    private lateinit var SharedPref: SharedPreferences
    private lateinit var SharedPrefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }


    private fun setUpView() {
        val colors =
            mutableListOf(Constants.COLOR_BLUE, Constants.COLOR_PURPLE, Constants.COLOR_RED)
        rvColors.adapter = ColorAdapter(colors, this)
        rvColors.layoutManager = LinearLayoutManager(this)
    }

    override fun pickedColor(color: String) {
        when (color) {
            Constants.COLOR_BLUE -> setPref(Constants.COLOR_BLUE)
            Constants.COLOR_PURPLE -> setPref(Constants.COLOR_PURPLE)
            Constants.COLOR_RED -> setPref(Constants.COLOR_RED)
        }
    }

    private fun setPref(color: String) {
        SharedPref = this.getSharedPreferences("com.example.colorfulnote.preferencefile", 0)
        SharedPrefEditor = SharedPref.edit()
        SharedPrefEditor.putString("color", color)
        SharedPrefEditor.apply()


        val intent = Intent(this, NoteTakingActivity::class.java)
        startActivity(intent)
    }
}
