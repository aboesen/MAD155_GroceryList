package com.example.grocerylist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyCustomAdapter(
    var ctx: Context,
    var Items: ArrayList<Model>
): RecyclerView.Adapter<MyCustomAdapter.ItemHolder>()
{


    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val store_name = itemView.findViewById<TextView>(R.id.store_name)
        val item_full_name = itemView.findViewById<TextView>(R.id.item_name)
        val quantity = itemView.findViewById<TextView>(R.id.quantity)
        val qDescription = itemView.findViewById<TextView>(R.id.qDescription)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemholder = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_layout, parent, false)
        return ItemHolder(itemholder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.store_name.text = Items[position].store_name
        holder.item_full_name.text = Items[position].item_full_name
        holder.quantity.text = Items[position].quantity
        holder.qDescription.text = Items[position].qDescription

        holder.itemView.setOnClickListener(View.OnClickListener {
            row_index = position
            notifyDataSetChanged()
        })
        if (row_index === position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#6200ee"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))
        }

    }

    override fun getItemCount(): Int {
        return Items.size
    }
}