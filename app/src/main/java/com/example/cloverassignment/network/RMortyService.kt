package com.example.cloverassignment.network


import com.example.cloverassignment.models.RMortyCharacterResponse
import com.example.cloverassignment.models.RMortyLocation
import com.example.cloverassignment.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RMortyService {

    /** A function to create Retrofit instance
    **/
    private fun retrofitService(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /** A function to get the CharacterResponse from api
     **/
    fun getRMortyCharacters(): Single<RMortyCharacterResponse>{
        return retrofitService().create(RMortyApi::class.java).getRMortyCharacters()
    }

    /** A function to get the LocationDetailResponse from api
     **/
    fun getLocation(locationUrl: String?): Single<RMortyLocation>{
        return retrofitService().create(RMortyApi::class.java).getLocationDetails(locationUrl)
    }
}