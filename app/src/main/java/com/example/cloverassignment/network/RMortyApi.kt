package com.example.cloverassignment.network


import com.example.cloverassignment.models.RMortyCharacterResponse
import com.example.cloverassignment.models.RMortyLocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RMortyApi {

    @GET("character")
    fun getRMortyCharacters(): Single<RMortyCharacterResponse>

    @GET
    fun getLocationDetails(@Url url: String?): Single<RMortyLocation>
}