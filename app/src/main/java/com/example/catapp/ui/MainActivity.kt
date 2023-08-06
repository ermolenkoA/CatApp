package com.example.catapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.domain.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadCats(1, 15)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.catsData.observe(this) { catsList ->
            catsList?.let {
                catAdapter.differ.submitList(catsList)
            }
        }

        viewModel.loadingState.observe(this){isLoading ->
            binding.mainScreenProgressBar.visibility = if(isLoading){
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }
    private fun setupViews() {
        catAdapter = CatAdapter(this)

        with(binding.catsRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }

    }
}