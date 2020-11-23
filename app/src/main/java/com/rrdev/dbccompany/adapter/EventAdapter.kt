package com.rrdev.dbccompany.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rrdev.dbccompany.R
import com.rrdev.dbccompany.util.formatOnPattern
import com.rrdev.domain.model.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_event.view.*

class EventAdapter: RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    var onItemClickListener: ((eventId: String) ->Unit) ? = null
    var listItems = emptyList<Event>()
        set(value) {
            val diffUtil = DiffUtil.calculateDiff(EventDiffUtilCallback(listItems, value))
            diffUtil.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.adapter_event, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(listItems[position])

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener { onItemClickListener?.invoke(listItems[adapterPosition].id) }
        }

        fun bind(event: Event){
            view.title.text = event.title
            view.dateOcurrency.text = event.date.formatOnPattern()
            view.numberOfPeople.text = if (event.people.isEmpty()){
                "Seja o primeiro a registrar no avento"
            } else{
                "${event.people.size} pessoas comparecerão ao evento."
            }
            Picasso.get()
                .load(event.image)
                .placeholder(R.drawable.ic_event_placeholder)
                .into(view.image)
        }
    }
}