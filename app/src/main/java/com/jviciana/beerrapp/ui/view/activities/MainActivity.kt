package com.jviciana.beerrapp.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jviciana.beerrapp.databinding.ActivityMainBinding
import com.jviciana.beerrapp.onTextChanged
import com.jviciana.beerrapp.ui.view.adapters.BeerAdapter
import com.jviciana.beerrapp.ui.viewmodel.BeerViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsRecyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = BeerAdapter(emptyList())
        binding.itemsRecyclerView.adapter = adapter

        beerViewModel.items.observe(this, Observer {
            adapter.updateItems(it)
        })

        beerViewModel.isLoading.observe(this,Observer{
            binding.progress.isVisible=it
        })

        binding.searchEditText.onTextChanged {
            beerViewModel.loadItems(it)
        }


    }
}