package com.example.randomduck.data.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomduck.data.api.DuckRepository
import com.example.randomduck.data.api.DuckResponse
import com.example.randomduck.data.model.Duck
import kotlinx.coroutines.launch

class DuckListViewModel(): ViewModel(){
    private val repository = DuckRepository.getInstance()
    private val _duck = MutableLiveData<Duck>()
    val duck: LiveData<Duck>
        get() = _duck

    private fun mapDuck(d: DuckResponse): Duck = Duck(
        d.url
    )

    init {

    }

    fun fetchOnlyOneDuck(url: String) {
        viewModelScope.launch {
            try {
                val duckResponse = repository.fetchOnlyOneDuck(url)
                //_duck.value = mapDuck(duck.value.url)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}