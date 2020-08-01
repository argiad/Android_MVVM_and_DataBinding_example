package com.crtmg.myapplicationmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crtmg.myapplicationmvvm.data.Food
import com.crtmg.myapplicationmvvm.databinding.ItemBinding
import com.crtmg.myapplicationmvvm.model.NutritionalPlanViewModel
import java.util.*

class MyAdapter(private val dataModel: NutritionalPlanViewModel) :
    RecyclerView.Adapter<MyAdapter.ItemViewHolder>(), Observer {

    init {
        dataModel.addObserver(this)
    }

    inner class ItemViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Food) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataModel.nutritionalPlan.foods.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(dataModel.nutritionalPlan.foods[position])

    override fun update(o: Observable?, arg: Any?) {
        notifyDataSetChanged()
    }
}