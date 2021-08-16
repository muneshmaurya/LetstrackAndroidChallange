package com.munesh.letstrackandroidchallenge.ui.drivers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.munesh.letstrackandroidchallenge.databinding.ActivityDriversBinding

class DriversActivity : AppCompatActivity() {

    private lateinit var customerId: String
    private lateinit var token: String
    private lateinit var viewModel: DriverViewModel
    private lateinit var binding: ActivityDriversBinding
    private lateinit var driverInfoAdapter: DriverListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customerId = intent.extras?.getString("customerId", "").toString()
        token = intent.extras?.getString("token", "").toString()

        viewModel = ViewModelProvider(this).get(DriverViewModel::class.java)
        viewModel.fetchDriverList(customerId, token)

        binding.loading.visibility = View.VISIBLE
        viewModel.getDriverList().observe(this@DriversActivity, Observer {
            if (it != null) {
                val driverList = it
                driverInfoAdapter = DriverListAdapter(this, driverList)
                binding.rvDrivers.layoutManager =
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                binding.rvDrivers.adapter = driverInfoAdapter
            }
            binding.loading.visibility = View.GONE
        })

        binding.btnViewType.setOnClickListener {
            changeView(binding.btnViewType.text.toString())
        }
    }

    private fun changeView(viewType:String) {
        if (viewType.equals( "GRID", true)) {
            binding.rvDrivers.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rvDrivers.adapter = driverInfoAdapter
            driverInfoAdapter.setGridView(true)
            binding.btnViewType.text = "LIST"
        } else if (viewType.equals( "LIST", true)) {
            binding.rvDrivers.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            binding.rvDrivers.adapter = driverInfoAdapter
            driverInfoAdapter.setGridView(false)
            binding.btnViewType.text = "GRID"
        }
    }
}