package com.example.event.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.ui.network.entity.Event
import com.squareup.picasso.Picasso

class EventAdapter(private var listOfEvents: List<Event>, val mainFragmentView: MainView) :
    RecyclerView.Adapter<EventAdapter.EventHolder>() {

    inner class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventImageView =
            itemView.findViewById<ImageView>(R.id.event_image_view)
        private val nameOfEventTextView =
            itemView.findViewById<TextView>(R.id.name_of_event_text_view)
        private val typeOfEventTextView =
            itemView.findViewById<TextView>(R.id.type_of_event_text_view)
        private val parentContainerConstraint =
            itemView.findViewById<ConstraintLayout>(R.id.parent_container_constraint)

        @SuppressLint("SetTextI18n")
        internal fun bind(item: Event) {
            Picasso.get().load(item.actor.avatar_url).into(eventImageView)
            nameOfEventTextView.text = item.repo.name
            typeOfEventTextView.text = "${item.type} ${item.created_at}"
            parentContainerConstraint.setOnClickListener {
                mainFragmentView.openEventFragment(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_list_item, parent, false)

        return EventHolder(v)
    }

    override fun getItemCount(): Int {
        return listOfEvents.size
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(listOfEvents[position])
    }
}