package com.example.cloverassignment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A Data Model Class for Location. We will use this in RMortyCharacter
 * data class
 */
@Parcelize
data class Location(
    val name:String?,
    val url:String?
):Parcelable
