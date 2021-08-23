package com.example.cloverassignment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

/**
 * A Data Model Class for RMortyCharacterResponse. We will use this to capture
 * list of Character details response
 */
@Parcelize
data class RMortyCharacterResponse(
        val info:Info,
        val results: ArrayList<RMortyCharacter>
    ):Parcelable