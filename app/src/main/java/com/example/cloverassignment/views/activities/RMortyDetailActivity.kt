package com.example.cloverassignment.views.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cloverassignment.R
import com.example.cloverassignment.models.RMortyCharacter
import com.example.cloverassignment.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_r_morty_detail.*

class RMortyDetailActivity : BaseActivity() {
    lateinit var mViewModel: ListViewModel
    var rMortyCharacterModel : RMortyCharacter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is used to align the xml view to this class
        setContentView(R.layout.activity_r_morty_detail)

        cont_view.visibility = View.GONE

        // get the Parcelable data model class with the details in it
        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAIL)){
            rMortyCharacterModel = intent.getParcelableExtra(
                MainActivity.EXTRA_PLACE_DETAIL) as RMortyCharacter
        }

        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        mViewModel.fetchLocationDetails(rMortyCharacterModel?.location?.url)

        updateUI()
    }

    private fun updateUI(){
        mViewModel.location
            .observe(this, Observer { it ->
                it?.let {
                    cont_view.visibility = View.VISIBLE
                    tv_locationName.text = rMortyCharacterModel!!.location?.name
                    tv_locationDimension.text = it.dimension
                    tv_locationType.text = it.type
                    tv_residents.text = it.residents!!.size.toString()
                    Glide
                        .with(this)
                        .load(rMortyCharacterModel!!.image)
                        .into(iv_place_image)
                }

            })

        mViewModel.loading.observe(this, Observer {isLoading->
            isLoading?.let {
                if (it) {
                    showProgressDialog(resources.getString(R.string.please_wait))
                }
                else{
                    hideProgressDialog()
                }
            }
        })

    }
}