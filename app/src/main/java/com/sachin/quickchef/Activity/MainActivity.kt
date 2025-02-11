package com.sachin.quickchef.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.quickchef.Adapter.CategoryAdapter
import com.sachin.quickchef.Adapter.TopDoctorAdapter
import com.sachin.quickchef.ViewModel.MainViewModel
import com.sachin.quickchef.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, TopDoctorsActivity::class.java))
        }
    }

    private fun initTopDoctors() {
        binding.apply {
            viewModel.doctors.observe(this@MainActivity, Observer {
                recyclerViewTopServices.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                recyclerViewTopServices.adapter=TopDoctorAdapter(it)
                progressBarTopServices.visibility=View.GONE
            })

            viewModel.loadDoctors()

            doctorListTxt.setOnClickListener {
                startActivity(Intent(this@MainActivity,TopDoctorsActivity::class.java))
            }

        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
           viewModel.category.observe(this, Observer {
               binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
               binding.viewCategory.adapter= CategoryAdapter(it)
               binding.progressBarCategory.visibility = View.GONE
           })
        viewModel.loadCategory()
    }
}