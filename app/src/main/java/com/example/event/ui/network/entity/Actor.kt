package com.example.event.ui.network.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("display_login") val display_login: String,
    @SerializedName("gravatar_id") val gravatar_id: String,
    @SerializedName("url") val url: String,
    @SerializedName("avatar_url") val avatar_url: String
) : Parcelable