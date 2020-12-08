package com.example.event.ui.network.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Payload(
    var push_id: @RawValue Long = 0,
    var size: @RawValue Long = 0,
    var distinct_size: @RawValue Long = 0,
    var ref: @RawValue String = "",
    var head: @RawValue String = "",
    var before: @RawValue String = "",
    var commits: @RawValue List<Commit>,
) : Parcelable

