package com.example.bst.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bst.DialogBox
import com.example.bst.R
import com.example.bst.TimeModel
import com.google.android.material.button.MaterialButton

class TimeAdapter(

    private val context: Context,
    private val dataSet: List<TimeModel>

) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    inner class TimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textPlace: TextView = view.findViewById(R.id.text_place)
        val textTime: TextView = view.findViewById(R.id.text_time)
        val btnEdit: MaterialButton = view.findViewById(R.id.btn_edit)
        val btnDelete: MaterialButton = view.findViewById(R.id.btn_delete)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_activity, parent, false)

        return TimeViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {

        val dialog = DialogBox()
        val time = dataSet[position]

        holder.textTime.text = time.time
        holder.textPlace.text = time.place

        holder.btnEdit.setOnClickListener {
            dialog.editDialog(context, time)
        }

        holder.btnDelete.setOnClickListener {
            dialog.deleteDialog(context, time)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}


