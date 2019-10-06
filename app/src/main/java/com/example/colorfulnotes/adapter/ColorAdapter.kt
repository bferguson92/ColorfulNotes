package com.example.colorfulnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.colorfulnotes.R
import com.example.colorfulnotes.util.Constants

class ColorAdapter(private val colors: List<String>, private val delagtor: ColorAdapterDelegate) :
    RecyclerView.Adapter<ColorAdapter.CustomViewHolder>() {
    private lateinit var context: Context


    interface ColorAdapterDelegate {
        fun pickedColor(color: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.color_list_item, parent, false)
        context = parent.context.applicationContext
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.colorBtn.text = colors[position]

        when (colors[position]) {
            Constants.COLOR_BLUE -> {
                holder.colorBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.prefBlue
                    )
                )

                holder.colorBtn.setOnClickListener{
                    delagtor.pickedColor(Constants.COLOR_BLUE)
                }
            }

            Constants.COLOR_PURPLE -> {
                holder.colorBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.prefPurple
                    )
                )

                holder.colorBtn.setOnClickListener{
                    delagtor.pickedColor(Constants.COLOR_PURPLE)
                }
            }
            Constants.COLOR_RED -> {
                holder.colorBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.prefRed
                    )
                )
                holder.colorBtn.setOnClickListener {
                    delagtor.pickedColor(Constants.COLOR_RED)
                }
            }
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorBtn: Button = view.findViewById(R.id.btnColor)
    }
}