package com.rrdev.dbccompany.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rrdev.domain.model.Event

class EventDiffUtilCallback(private val oldList: List<Event>, private val newList: List<Event>): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
}