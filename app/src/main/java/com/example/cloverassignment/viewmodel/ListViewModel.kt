package com.example.cloverassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cloverassignment.R
import com.example.cloverassignment.models.RMortyCharacterResponse
import com.example.cloverassignment.models.RMortyLocation
import com.example.cloverassignment.utils.Constants
import com.example.cloverassignment.network.RMortyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel:ViewModel() {

    val character = MutableLiveData<RMortyCharacterResponse>()
    val location = MutableLiveData<RMortyLocation>()
    private val compositeDisposable = CompositeDisposable()
    private var rMortyService = RMortyService()
    val loading = MutableLiveData<Boolean>()

    /** A function to fetch details of all character from api and giving
     * the result to UI.
     **/
    fun fetchCharacters(){
        loading.value = true
        addToDisposable(
                rMortyService.getRMortyCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<RMortyCharacterResponse>() {
                    override fun onSuccess(value: RMortyCharacterResponse) {
                        character.value = value
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loading.value = false
                        Log.i(Constants.ERROR, R.string.error_in_rx_java.toString())
                    }
                } )
        )
    }

    /** A function to fetch details of location for a particular character
     * from api and giving the result to UI i.e.RMortyDetailActivity
     **/
    fun fetchLocationDetails(url:String?){
        loading.value = true
        addToDisposable(
                rMortyService.getLocation(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<RMortyLocation>() {
                    override fun onSuccess(value: RMortyLocation) {
                        location.value = value
                        loading.value = false
                    }
                    override fun onError(e: Throwable?) {
                        Log.i(Constants.ERROR, R.string.error_in_rx_java.toString())
                        loading.value = false
                    }
                })
        )
    }

    private fun addToDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }
}




