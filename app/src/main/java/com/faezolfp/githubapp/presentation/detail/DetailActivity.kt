package com.faezolfp.githubapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.faezolfp.githubapp.R
import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.utils.TransactionDbFor
import com.faezolfp.githubapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailActivityViewModel by viewModels()
    private lateinit var dataDetailUser: ModelDetailUser
    private lateinit var transaction: TransactionDbFor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail User"

        val dataLogin = intent.getStringExtra(EXTRA_NAME)
        Log.d("TRACK", dataLogin.toString())
        if (dataLogin != null){
            observeViewModel(dataLogin)
        }
        setUpButton()
    }

    private fun setUpButton() {
        binding.itmDetail.btnFavorite.setOnClickListener {
            val data = ModelDataUser(
                avatarUrl = dataDetailUser.avatarUrl,
                id = dataDetailUser.id,
                login = dataDetailUser.login
            )

            Toast.makeText(this, transaction.name.toString()+data.id.toString(), Toast.LENGTH_SHORT).show()
            viewModel.transaction(transaction, data)
            observerDataIsAvailable()
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
                    dataDetailUser = dataDetail.data!!
                    Glide.with(this)
                        .load(dataDetail.data?.avatarUrl)
                        .into(binding.itmDetail.imgDetaiUser)
                    binding.apply {
                        itmDetail.txtNameUser.text = dataDetail.data?.name
                        itmDetail.txtUsernameUser.text = dataDetail.data?.login
                        itmDetail.txtBioUser.text = dataDetail.data?.bio
                    }
                    observerDataIsAvailable()
                }
            }
        }

    }

    private fun observerDataIsAvailable(){
        //observer dataAVAILABLE
        viewModel.dataIsAvailable(dataDetailUser.id.toString()).observe(this){isAvailable ->
            when(isAvailable) {
                true -> {
                    transaction = TransactionDbFor.FORDELETEUSER
                    binding.itmDetail.btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.baseline_bookmark_24))
                }
                false -> {
                    transaction = TransactionDbFor.FORADDUSER
                    binding.itmDetail.btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.baseline_bookmark_border_24))

                }
            }
        }
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}