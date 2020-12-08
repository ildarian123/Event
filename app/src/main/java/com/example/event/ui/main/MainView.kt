package com.example.event.ui.main

import com.example.event.ui.network.entity.Event


interface MainView {
    fun openEventFragment(event: Event)
}