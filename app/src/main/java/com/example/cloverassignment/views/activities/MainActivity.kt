package com.example.cloverassignment.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloverassignment.R
import com.example.cloverassignment.models.RMortyCharacter
import com.example.cloverassignment.viewmodel.ListViewModel
import com.example.cloverassignment.views.adapter.RMortyCharacterAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mAdapter = RMortyCharacterAdapter(this,arrayListOf())
    lateinit var mViewModel : ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)


        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        mViewModel.fetchCharacters()

        setupAdapter()
        populateRMCharacterListToUI()

        // Setting an click event for list item and calling the RMortyDetailActivity.
        mAdapter.setOnClickListener(object : RMortyCharacterAdapter.OnClickListener {
            override fun onClick(position: Int, model: RMortyCharacter) {
                val intent = Intent(this@MainActivity, RMortyDetailActivity::class.java)
                intent.putExtra(EXTRA_PLACE_DETAIL!!,model)
                startActivity(intent)
            }
        })


    }

    /**
     * A function to setup the adapter to the UI.
     */
    private fun setupAdapter() {
        val viewModel =mViewModel
        if(viewModel!=null)
        {
            rv_view.setHasFixedSize(true)
            rv_view.layoutManager = LinearLayoutManager(this@MainActivity)
            rv_view.adapter = mAdapter
        }
    }

    /**
     * A function which observes the character in viewModel and onSuccess sends the
     * result to Adapter then updates the UI i.e in the recyclerView
     */
    private fun populateRMCharacterListToUI(){

        mViewModel.character
            .observe(this, Observer { it ->
                it?.let {
                    if(it.results.isNotEmpty()){
                        tv_no_data_available.visibility = View.GONE
                        rv_view.visibility = View.VISIBLE
                        mAdapter.updateList(it.results)
                    }
                    else{
                        tv_no_data_available.visibility = View.VISIBLE
                        rv_view.visibility = View.GONE
                    }


                }

            })

        mViewModel.loading.observe(this, Observer {isLoading->
            isLoading?.let {
                if (it) {
                    // Show the progress dialog.
                    showProgressDialog(resources.getString(R.string.please_wait))
                }
                else{
                   hideProgressDialog()
                }
            }
        })
    }

    /**
     * A companion object to declare the constants.
     */
    companion object {
        var EXTRA_PLACE_DETAIL = "extra_place_detail"
    }


}