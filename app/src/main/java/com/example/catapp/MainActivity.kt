package com.example.catapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.catapp.domain.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadCats(1, 15)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.catsData.observe(this) {
            it?.let {
                Log.d("TAG", it.toString())
            }
        }
    }
}