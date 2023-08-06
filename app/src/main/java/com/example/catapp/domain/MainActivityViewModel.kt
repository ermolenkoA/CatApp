package com.example.catapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.remote.data.CatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: CatRepository): ViewModel() {
    private var _catsData = MutableLiveData<List<CatModel>?>(null)
    val catsData: LiveData<List<CatModel>?> get() = _catsData

    private var _loadingState = MutableLiveData<Boolean>(false)
    val loadingState: LiveData<Boolean> get() = _loadingState

    private var shouldRefresh = true
    fun loadCats(page: Int, amount: Int) {
        viewModelScope.launch {
            _loadingState.value = true

            while (shouldRefresh) {
                val response = repository.getCats(page, amount)
                if(response.isSuccessful) {
                    _catsData.value = response.body()
                } else {
                    _catsData.value = null
                }
                _loadingState.value = false
                delay(20_000)
            }

        }
    }
}