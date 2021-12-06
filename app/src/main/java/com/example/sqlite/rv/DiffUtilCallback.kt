package com.example.sqlite.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.sqlite.model.DataModel

class DiffUtilCallback : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean = oldItem.name == newItem.name


    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean = areItemsTheSame(oldItem, newItem)

}