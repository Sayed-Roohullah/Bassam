package com.acclivousbyte.bassam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.models.genrelModels.Children
import com.bumptech.glide.Glide

class ChildAdapter(val context: Context, var childlist : List<Children>
) : RecyclerView.Adapter<ChildAdapter.MyViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.childern_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = childlist[position]

        holder.childname.text = current.name

        if (current.profile_picture_square == "") {
            holder.childimage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.default_image
                )
            )

        } else {
            Glide.with(context).load(current.profile_picture_square).into(holder.childimage)

        }

    }


    override fun getItemCount(): Int {
        return childlist.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var childimage = itemView.findViewById<ImageView>(R.id.childimage)
        var childname = itemView.findViewById<TextView>(R.id.childname)
     }


}