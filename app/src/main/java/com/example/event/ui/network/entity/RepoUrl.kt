package com.example.event.ui.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoUrl(
    val html_url: String = ""
) : Parcelable