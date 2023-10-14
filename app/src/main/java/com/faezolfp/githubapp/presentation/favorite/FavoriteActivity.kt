package com.faezolfp.githubapp.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.ui.FavoriteActivityAdapter
import com.faezolfp.githubapp.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpInit()
        observerViewModel()
    }

    private fun setUpInit() {
        binding.rvDataUser.apply {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
        }
    }

    override fun onResume() {
        super.onResume()
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.getListUser().observe(this) { data ->
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
//                        adapter.setData(data.data)
                        adapter = FavoriteActivityAdapter()
                        binding.rvDataUser.adapter = adapter
                        adapter.submitList(data.data)

                    }
                    Log.d("TRACK", data.data.toString())
                }
            }
        }
    }
}