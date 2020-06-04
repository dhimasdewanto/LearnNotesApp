package com.dhimasdewanto.learnnotesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dhimasdewanto.learnnotesapp.R
import com.dhimasdewanto.learnnotesapp.presentation.create.CreateFragment
import com.dhimasdewanto.learnnotesapp.presentation.home.HomeFragment
import com.dhimasdewanto.learnnotesapp.presentation.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNav()
        setDefaultFragment()
    }

    private fun setBottomNav() {
        nav_menu_main.setOnNavigationItemSelectedListener(navListener)
    }

    private fun setDefaultFragment() {
        supportActionBar?.title = "Home"
        changeFragment(HomeFragment())
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        val selectedFragment: Fragment = when (menuItem.itemId) {
            R.id.menu_home -> {
                supportActionBar?.title = "Home"
                HomeFragment()
            }
            R.id.menu_create -> {
                supportActionBar?.title = "Create New Note"
                CreateFragment()
            }
            R.id.menu_settings -> {
                supportActionBar?.title = "Settings"
                SettingsFragment()
            }
            else -> HomeFragment()
        }

        changeFragment(selectedFragment)

        return@OnNavigationItemSelectedListener true
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit()
    }
}
