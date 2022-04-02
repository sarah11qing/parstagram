package com.example.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagram.fragments.ComposeFragment
import com.example.parstagram.fragments.FeedFragment
import com.example.parstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

/**
 * Let user create a post by taking a photo with their camera
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Set the description of the post
        // 2. A button to launch the camera to take a picture
        // 3. An ImageView to show the picture the user has taken
        // 4. A button to save and send the post to our Parse server

        var fragementManager: FragmentManager = supportFragmentManager


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->
            // Return true to say that we've handled this user interaction on the item
            var fragmentToShow: Fragment? = null

            when (item.itemId) {

                R.id.action_home -> {
                    // Navigate to the home screen
                    fragmentToShow = FeedFragment()
                }
                R.id.action_compose -> {
                    // Navigate to the compose screen
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    // Navigate to the profile screen
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow != null) {
                fragementManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            true
        }


        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }

    companion object {
        const val TAG = "MainActivity"
    }
}