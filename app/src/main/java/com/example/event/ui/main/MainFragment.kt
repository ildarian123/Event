package com.example.event.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event.R
import com.example.event.ui.network.entity.Event
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainView {

    private var dataWasLoaded: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("query", "onViewCreated")
        setListener()
        val model: EventsViewModel by viewModels()
        model.getEventsData.observe(viewLifecycleOwner, {
            when (it.code()) {
                200 -> {
                    dataWasLoaded = true
                    rv_list_of_events.layoutManager =
                        LinearLayoutManager(activity?.applicationContext)
                    rv_list_of_events.adapter = EventAdapter(it.body() ?: mutableListOf(), this)
                    (rv_list_of_events.adapter as EventAdapter).notifyDataSetChanged()
                    Log.v("query", "Ok")
                }
                403 -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        getString(R.string.APIRateLimitExceeded),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v("query", "null")
                }
                else -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        getString(R.string.some_problem),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        if (!dataWasLoaded)
            model.getEventsData()
    }

    private fun setListener() {
    }

    override fun openEventFragment(event: Event) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToEventFragment(event))
    }
}