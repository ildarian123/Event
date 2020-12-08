package com.example.event.ui.network.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(

    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("actor") val actor: Actor,
    @SerializedName("repo") val repo: Repo,
    @SerializedName("payload") val payload: Payload,
    @SerializedName("public") val public: Boolean,
    @SerializedName("created_at") val created_at: String

) : Parcelable