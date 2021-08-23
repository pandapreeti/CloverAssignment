package com.example.cloverassignment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A Data Model Class for RMortyCharacter. We will use this in RMortyCharacterResponse
 * data class
 */
@Parcelize
data class RMortyCharacter(
    val name:String?,
    val status:String?,
    val species:String?,
    val location:Location?,
    val image:String?
):Parcelable
