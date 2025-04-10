package com.creativeitinstitute.elexaamart.views.dashboard.seller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.creativeitinstitute.elexaamart.R
import com.creativeitinstitute.elexaamart.databinding.ActivitySellerDashboardBinding
import com.creativeitinstitute.elexaamart.views.starter.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashboard : AppCompatActivity() {
    private lateinit var binding: ActivitySellerDashboardBinding
    lateinit var navController: NavController


    @Inject
    lateinit var jAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView2)
        val appBarConfig = AppBarConfiguration(setOf(
            R.id.product,
            R.id.add,
            R.id.profile
        ))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.seller_top_menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.logout->{
                jAuth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.settings->{
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_LONG).show()
            }
            R.id.report->{
                Toast.makeText(this, "Report Clicked", Toast.LENGTH_LONG).show()
            }


        }


        return super.onOptionsItemSelected(item)
    }
}