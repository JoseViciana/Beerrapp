package com.jviciana.beerrapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jviciana.beerrapp.data.model.BeerFactory
import com.jviciana.beerrapp.data.model.BeerModel
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private var beerFactory: BeerFactory
):ViewModel() {

    val beerModel = MutableLiveData<List<BeerModel>>()
    val items: LiveData<List<BeerModel>> = beerModel
    val isLoading = MutableLiveData<Boolean>()


    fun loadItems(name:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = beerFactory.create(name)
            if(!result.invoke().isNullOrEmpty()){
                beerModel.postValue(result.invoke())
            }else{
                beerModel.postValue(emptyList())
            }
            isLoading.postValue(false)
        }
    }
}