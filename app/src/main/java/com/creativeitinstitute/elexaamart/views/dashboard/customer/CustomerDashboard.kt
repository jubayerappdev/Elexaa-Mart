package com.creativeitinstitute.elexaamart.views.dashboard.customer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.databinding.ActivityCustomerDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}