package com.example.usha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.usha.community.CommunityFragment
import com.example.usha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setNAvigation()
        supportFragmentManager.beginTransaction().add(R.id.linearForFragment , CommunityFragment()).commit()
    }

    fun setNAvigation(){
        binding!!.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.community -> {
                    supportFragmentManager.beginTransaction().add(R.id.linearForFragment , CommunityFragment()).commit()
                    true
                }
                R.id.notification -> {
                    supportFragmentManager.beginTransaction().add(R.id.linearForFragment , CommunityFragment()).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().add(R.id.linearForFragment , CommunityFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}