package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding
    private lateinit var  photoAdapter : PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        photoAdapter = PhotoAdapter()
        binding.rvPhotos.adapter = photoAdapter
        binding.rvPhotos.layoutManager = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launch {
            viewModel.getPhoto().collect{
                photoAdapter.submitData(it)
            }
        }

    }
}