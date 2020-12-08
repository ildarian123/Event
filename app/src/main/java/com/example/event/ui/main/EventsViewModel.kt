package com.example.event.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.event.ui.network.EventApi
import com.example.event.ui.network.entity.Event
import com.example.event.ui.network.entity.RepoUrl
import com.example.event.ui.provider.StorageProvider
import kotlinx.coroutines.launch
import retrofit2.Response

class EventsViewModel : BaseViewModel() {

    private var eventApi: EventApi = StorageProvider.provideEventApi()

    val getEventsData = MutableLiveData<Response<List<Event>>>()
    val getRepoData = MutableLiveData<Response<RepoUrl>>()

    fun getEventsData() {
        uiScope.launch {
            Log.v("query", "get corutine")
            getEventsData.value = eventApi.getEvents()
        }
    }

    fun getRepoData(url: String) {
        uiScope.launch {
            val repo = eventApi.getRepo(url)
            getRepoData.value = repo
        }
    }
}