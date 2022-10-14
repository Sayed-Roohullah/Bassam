package com.acclivousbyte.bassam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.models.genrelModels.Sibling

class SiblingAdapter(val context: Context, var siblinglist : List<Sibling>
) : RecyclerView.Adapter<SiblingAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.sibling_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = siblinglist[position]

        holder.childname.text = current.name

    }


    override fun getItemCount(): Int {
        return siblinglist.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var childname = itemView.findViewById<TextView>(R.id.childname)
    }
}