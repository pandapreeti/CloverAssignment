package com.example.cloverassignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A Data Model Class for Info. We will use this in RMortyCharacterResponse
 * data class
 */
@Parcelize
data class Info(
    val count:Int,
    val pages:Int,
    val next:String,
    val prev:String?
):Parcelable