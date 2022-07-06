package com.example.usha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.usha.community.CommunityFragment
import com.example.usha.databinding.ActivityMainBinding
import com.example.usha.notification.NotificationFragment
import com.example.usha.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.lifecycleOwner = this
//        setNAvigation()
//        replaceFragment(CommunityFragment(replaceFragment))

        // fragment controller 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding!!.bottomNavView.setupWithNavController(navController)
    }

//    fun setNAvigation() {
//        binding!!.bottomNavView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.community -> {
//                    replaceFragment(CommunityFragment(replaceFragment))
//                    true
//                }
//                R.id.notification -> {
//                    replaceFragment(NotificationFragment())
//                    true
//                }
//                R.id.profile -> {
//                    replaceFragment(ProfileFragment())
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//
//    var replaceFragment: (Fragment)->Int = {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.linearForFragment, it)
//            .commitAllowingStateLoss()
//    }
}