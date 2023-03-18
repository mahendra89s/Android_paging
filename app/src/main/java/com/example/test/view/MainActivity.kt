package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding
    private lateinit var  adapter : PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PhotoAdapter {
            viewModel.getPhotos(it)
        }
        binding.rvPhotos.adapter = adapter
        binding.rvPhotos.layoutManager = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        viewModel.photos.observe(this){
            adapter.setData(it)
        }
    }
}