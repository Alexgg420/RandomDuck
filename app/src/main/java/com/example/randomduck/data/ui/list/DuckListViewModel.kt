package com.example.randomduck.data.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomduck.data.api.DuckApiModel
import com.example.randomduck.data.api.DuckListApiModel
import com.example.randomduck.data.api.DuckRepository
import com.example.randomduck.data.model.Duck
import kotlinx.coroutines.launch

class DuckListViewModel(): ViewModel(){
    private val repository = DuckRepository.getInstance()
    private val _duck = MutableLiveData<List<Duck>>()
    val duck: LiveData<List<Duck>>
        get() = _duck

    private fun mapDuck(d: DuckApiModel): Duck = Duck(
        d.frontUrl
    )

    private val observer = Observer<DuckListApiModel> {
        _duck.value = it.list.map {
            mapDuck(it)
        }
    }

    init {
        fetchAll()
    }

    fun fetchOnlyOnePokemon(frontUrl: String) {
        viewModelScope.launch {
            repository.fetchOnlyOneDuck(frontUrl)
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            repository.pokemon.observeForever(observer)
            viewModelScope.launch {
                repository.fetchList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.pokemon.removeObserver(observer)
    }
}