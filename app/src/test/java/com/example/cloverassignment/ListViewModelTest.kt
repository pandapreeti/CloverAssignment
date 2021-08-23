package com.example.cloverassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cloverassignment.models.*
import com.example.cloverassignment.viewmodel.ListViewModel
import com.example.cloverassignment.network.RMortyService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var rMortyService1: RMortyService

    @InjectMocks
    val listViewModel1 = ListViewModel()

    private var testSingleCharacter: Single<RMortyCharacterResponse>?=null
    private var testSingleLocation: Single<RMortyLocation>?=null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCharactersSuccess(){
        val singleCharacter =RMortyCharacter("Preeti","Alive","Human", Location("LName","heello"),"src")
        val response =RMortyCharacterResponse(Info(108,6,"hello","23"),arrayListOf(singleCharacter))

        testSingleCharacter = Single.just(response)
        `when`(rMortyService1.getRMortyCharacters()).thenReturn(testSingleCharacter)
        listViewModel1.fetchCharacters()

        Assert.assertEquals(1,listViewModel1.character.value?.results!!.size)

    }

  @Test
    fun getLocationDetailsSuccess(){
      val residentsList= arrayListOf("https://www.google.com","https://www.google.com")
        val singleLocation = RMortyLocation("Planet","Dimension C-137",residentsList)

        testSingleLocation = Single.just(singleLocation)
        `when`(rMortyService1.getLocation("https://www.google.com")).thenReturn(testSingleLocation)
        listViewModel1.fetchLocationDetails("https://www.google.com")

        Assert.assertEquals(2,listViewModel1.location.value?.residents!!.size)

    }

    @Before
    fun setUpRxSchedulers(){
        val immediate = object: Scheduler(){

            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor{
                    it.run()
                })
            }

        }

        RxJavaPlugins.setInitIoSchedulerHandler{ scheduler-> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler{ scheduler-> immediate}
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler-> immediate }
        RxJavaPlugins.setSingleSchedulerHandler{ scheduler-> immediate}
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler-> immediate }
    }


}