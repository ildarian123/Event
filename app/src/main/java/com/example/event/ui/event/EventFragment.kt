package com.example.event.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.event.R
import com.example.event.ui.main.EventsViewModel
import com.example.event.ui.network.entity.Event
import kotlinx.android.synthetic.main.fragment_event.view.*

class EventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val model: EventsViewModel by viewModels()

        val view = inflater.inflate(R.layout.fragment_event, container, false)
        val event: Event? = requireArguments().getParcelable("data")

        model.getRepoData.observe(viewLifecycleOwner, Observer {

            val result = it.body()
            Log.v("qqqqqq", "result ${result}")
            when (it.code()) {
                200 -> {
                    view.repository_web_view.settings.setJavaScriptEnabled(true)
                    view.repository_web_view.webChromeClient = WebChromeClient()
                    view.repository_web_view.webViewClient = WebViewClient()
                    view.repository_web_view.loadUrl(it.body()?.html_url)
                    Log.v("query", "Ok")
                    Log.v("qqqqqq", "get url for web view Success ${it.body()?.html_url}")
                }
                403 -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        "API rate limit exceeded",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v("qqqqqq", "get url for web view failed")
                }
                else -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Some problem",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        Log.v("qqqqqq", "repo url ${event?.repo?.url.toString()}")
        model.getRepoData(event?.repo?.url.toString())

        return view
    }
}