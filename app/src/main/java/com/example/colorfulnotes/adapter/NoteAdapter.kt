package com.example.colorfulnotes.adapter

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.colorfulnotes.R
import com.example.colorfulnotes.util.Constants
import com.example.colorfulnotes.util.Constants.COLOR_PURPLE
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteAdapter(private val notes: List<String>, private val delegator: NoteAdapterDelagte) :
    RecyclerView.Adapter<NoteAdapter.CustomViewHolder>() {
    private lateinit var context: Context
    private lateinit var sharedPref: SharedPreferences
    interface NoteAdapterDelagte{
        fun onNoteClick(text: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        context = parent.context.applicationContext
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            sharedPref = context.getSharedPreferences("com.example.colorfulnotes.file", 0)
            val color = sharedPref.getString("color", "BLUE")

            when(color){
                COLOR_PURPLE -> noteText.setTextColor(ContextCompat.getColor(context, R.color.prefPurple))
                Constants.COLOR_RED -> noteText.setTextColor(ContextCompat.getColor(context, R.color.prefRed))
                Constants.COLOR_BLUE -> noteText.setTextColor(ContextCompat.getColor(context, R.color.prefBlue))

            }
            noteText.text = notes[position]
            noteText.setOnClickListener{
                delegator.onNoteClick(notes[position])
            }
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteText: TextView = view.findViewById(R.id.tvNote)
    }
}