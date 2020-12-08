package com.example.event.ui.network

import com.example.event.ui.network.entity.Event
import com.example.event.ui.network.entity.RepoUrl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EventApi {
    @GET("/events")
    suspend fun getEvents(): Response<List<Event>>?

    @GET
    suspend fun getRepo(@Url url: String): Response<RepoUrl>?
}