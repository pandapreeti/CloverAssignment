package com.example.cloverassignment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A Data Model Class for RMortyLocation. We will use this to capture
 * details of location
 */
@Parcelize
 data class RMortyLocation(
    val type:String?,
    val dimension:String?,
    val residents:ArrayList<String>?
):Parcelable
