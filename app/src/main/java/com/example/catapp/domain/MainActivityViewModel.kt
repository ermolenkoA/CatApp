package com.example.catapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapp.remote.data.CatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: CatRepository): ViewModel() {
    private var _catsData = MutableLiveData<List<CatModel>?>(null)
    val catsData: LiveData<List<CatModel>?> get() = _catsData

    fun loadCats(page: Int, amount: Int) {
        repository.getCats(page, amount).enqueue(
            object : Callback<List<CatModel>?> {
                override fun onResponse(
                    call: Call<List<CatModel>?>,
                    response: Response<List<CatModel>?>
                ) {
                    if (response.isSuccessful) {
                        _catsData.postValue(response.body())
                    } else {
                        _catsData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<CatModel>?>, t: Throwable) {
                    _catsData.postValue(null)
                }

            }
        )
    }
}