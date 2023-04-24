package com.example.movies.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.movies.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnHome : ImageView
    private lateinit var btnFavourite : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        btnHome.setOnClickListener {
            navController.navigate(R.id.mainFragment)
            btnHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_icon_pressed))
            btnFavourite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favourite_icon))
        }
        btnFavourite.setOnClickListener {
            navController.navigate(R.id.favouriteFragment)
            btnHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_icon))
            btnFavourite.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.favourite_icon_pressed
            ))
        }
    }

    fun initViews(){
        btnHome = findViewById(R.id.btnHome)
        btnFavourite = findViewById(R.id.btnFavourite)
    }
}