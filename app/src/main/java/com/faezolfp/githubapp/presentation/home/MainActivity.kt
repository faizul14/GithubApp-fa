package com.faezolfp.githubapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.ui.MainActivityAdapter
import com.faezolfp.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpInit()
        observerViewModel()
    }

    private fun setUpInit() {
        adapter = MainActivityAdapter()
        binding.rvDataUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun observerViewModel() {
        viewModel.dataListAppGithub().observe(this) { data ->
            when (data) {
                is Resource.Loading -> {
                    binding.rvDataUser.visibility = View.GONE
                    binding.progresLoading.visibility = View.VISIBLE
                }

                is Resource.Error -> {

                }

                is Resource.Success -> {
                    binding.progresLoading.visibility = View.GONE
                    binding.rvDataUser.visibility = View.VISIBLE
                    if (data.data!!.isNotEmpty()) {
                        adapter.setData(data.data)
                        binding.rvDataUser.adapter = adapter
                    }
                    Log.d("TRACK", data.data.toString())
                }
            }
        }
    }
}