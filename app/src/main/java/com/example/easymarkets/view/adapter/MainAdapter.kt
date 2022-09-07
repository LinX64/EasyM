package com.example.easymarkets.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easymarkets.data.model.Apartment
import com.example.easymarkets.databinding.ListItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private var mItems = ArrayList<Apartment>()

    inner class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Apartment) {
            binding.item = items
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    fun setData(list: List<Apartment>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }
}