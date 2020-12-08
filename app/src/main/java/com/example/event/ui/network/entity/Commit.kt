package com.example.event.ui.network.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Commit(
    @SerializedName("sha") val sha: String,
    @SerializedName("author") val author: Author,
    @SerializedName("message") val message: String,
    @SerializedName("distinct") val distinct: Boolean,
    @SerializedName("url") val url: String
) : Parcelable