package com.patrickmota.firebase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.patrickmota.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            binding.textViewNotification.text = bundle.getString("text")
        }

    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(messageReceiver, IntentFilter("MyData"))

    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)

    }

    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            binding.textViewNotification.text = intent.extras?.getString("message")
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}