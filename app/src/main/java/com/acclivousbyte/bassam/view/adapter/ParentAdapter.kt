package com.acclivousbyte.bassam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.models.genrelModels.Data
import com.acclivousbyte.bassam.utils.OnClickView
import com.bumptech.glide.Glide

class ParentAdapter(
    val context: Context,
     private val clickView: OnClickView
) : RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {

    var datalist= ArrayList<Data>()

    var templist = listOf<Data>()
    init {
        templist = datalist
    }

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.parent_layout, parent, false)
        val myholder = MyViewHolder(view)
        view.setOnClickListener {
            clickView.onclicklistener(datalist[myholder.adapterPosition])
        }
        return myholder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = datalist[position]
        holder.name_fa_gfa.text =
            current.name + " " + current.father_name + " " + current.grand_father_name +
                    " " + current.g_grand_father_name
        holder.node_Id.text = current.nodeID

        if (current.profile_picture_square == "") {
            holder.parentImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.default_image
                )
            )

        } else {
            Glide.with(context).load(current.profile_picture_square).into(holder.parentImage)

        }
        if (current.is_worthy == "Yes") {
            holder.worthy.visibility = View.VISIBLE
        } else {
            holder.worthy.visibility = View.GONE
        }
        if (current.alive == "No") {
            holder.deathperson.visibility = View.VISIBLE

        } else {
            holder.deathperson.visibility = View.GONE

        }


    }

    fun updatedList(newList: ArrayList<Data>) {
        //datalist.clear()
        datalist = newList
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return datalist.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var parentImage = itemView.findViewById<ImageView>(R.id.parentimage)
        var deathperson = itemView.findViewById<TextView>(R.id.deathperson)
        var worthy = itemView.findViewById<ImageView>(R.id.worthy_gray)
        var name_fa_gfa = itemView.findViewById<TextView>(R.id.name_fa_gfa)
        var node_Id = itemView.findViewById<TextView>(R.id.node_id)
    }


}
