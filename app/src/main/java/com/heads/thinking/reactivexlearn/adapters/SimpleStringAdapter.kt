package com.heads.thinking.reactivexlearn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heads.thinking.reactivexlearn.R

class SimpleStringAdapter(private val context : Context) : RecyclerView.Adapter<SimpleStringAdapter.ViewHolder>() {

    var mStrings : List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.string_list_item,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = mStrings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(mStrings[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(context, mStrings[position], Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var dataTextView: TextView
        fun setData(data : String) {
            dataTextView = itemView.findViewById(R.id.dataTextView)
            dataTextView.text = data
        }
    }
}