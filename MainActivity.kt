package com.example.szlaki_turystyczne

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.szlaki_turystyczne.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainFrag = MainFragment()
        binding.listaSciezek.text = "Lista ścieżek"

        supportFragmentManager.commit {
            add(R.id.fragmentContainerView, mainFrag)
        }
    }
}