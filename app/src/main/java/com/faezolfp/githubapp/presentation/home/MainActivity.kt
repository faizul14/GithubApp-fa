package com.faezolfp.githubapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.ui.MainActivityAdapter
import com.faezolfp.githubapp.databinding.ActivityMainBinding
import com.faezolfp.githubapp.presentation.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        setupButton()
    }

    private fun setupButton() {
        binding.apply {
            btnGotofav.setOnClickListener {
                val move = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(move)
            }
            edtUsername.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    lifecycleScope.launch {
                        viewModel.queryChannel.value = p0.toString()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }

    private fun observerViewModel() {
        observeDataListGithub()
        viewModel.trackTextChange2.observe(this) { textTrack ->
            if (!textTrack.equals("")) {
                viewModel.searchDataUser(textTrack).observe(this) { dataSearch ->
                    when (dataSearch) {
                        is Resource.Loading -> {
                            binding.rvDataUser.visibility = View.GONE
                            binding.progresLoading.visibility = View.VISIBLE
                        }

                        is Resource.Error -> {

                        }

                        is Resource.Success -> {
                            binding.progresLoading.visibility = View.GONE
                            binding.rvDataUser.visibility = View.VISIBLE
                            if (dataSearch.data!!.isNotEmpty()) {
                                adapter.setData(dataSearch.data)
                                binding.rvDataUser.adapter = adapter
                            }
                            Log.d("TRACK", dataSearch.data.toString())
                        }
                    }
                }
            } else {
                observeDataListGithub()
            }
        }
    }

    private fun observeDataListGithub() {
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