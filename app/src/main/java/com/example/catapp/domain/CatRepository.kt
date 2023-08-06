package com.example.catapp.domain

import com.example.catapp.remote.network.CatApiInterface
import javax.inject.Inject

class CatRepository @Inject constructor(private val apiInterface: CatApiInterface) {
    suspend fun getCats(page: Int, amount: Int) = apiInterface.getCats(amount, page)
}