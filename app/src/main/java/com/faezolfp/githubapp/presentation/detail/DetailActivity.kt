package com.faezolfp.githubapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.faezolfp.githubapp.R
import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataLogin = intent.getStringExtra(EXTRA_NAME)
        Log.d("TRACK", dataLogin.toString())
        if (dataLogin != null){
            observeViewModel(dataLogin)
        }
    }

    private fun observeViewModel(dataLogin: String) {
        viewModel.getDetailUser(dataLogin).observe(this){dataDetail ->
            when(dataDetail) {
                is Resource.Loading -> {
                    binding.itmDetail.view.visibility = View.GONE
                    binding.progresLoading.visibility = View.VISIBLE
                }

                is Resource.Error -> {

                }

                is Resource.Success -> {
                    binding.progresLoading.visibility = View.GONE
                    binding.itmDetail.view.visibility = View.VISIBLE
                    Glide.with(this)
                        .load(dataDetail.data?.avatarUrl)
                        .into(binding.itmDetail.imgDetaiUser)
                    binding.apply {
                        itmDetail.txtNameUser.text = dataDetail.data?.name
                        itmDetail.txtUsernameUser.text = dataDetail.data?.login
                        itmDetail.txtBioUser.text = dataDetail.data?.bio
                    }
                }
            }
        }
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}