package com.app.compare_my_trade.ui.postauthenticationui

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var backPressedTime:Long = 0
    private lateinit var binding: ActivityHomeBinding
    val navController by lazy { findNavController(R.id.nav_host_fragment_activity_home) }
     // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    private val     appBarConfiguration by lazy {
         AppBarConfiguration(
             setOf(
                 R.id.navigation_home, R.id.navigation_manage_bids, R.id.navigation_notifications, R.id.navigation_more
             )
         )
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.app.appbar)
        val navView: BottomNavigationView = binding.navView

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}