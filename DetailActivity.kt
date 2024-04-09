package com.example.szlaki_turystyczne

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.szlaki_turystyczne.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nazwa = intent.getStringExtra("nazwa")
        val opis = intent.getStringExtra("opis")
        if (savedInstanceState == null) {
            val detailFrag = DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("nazwa", nazwa)
                    putString("opis", opis)
                }
            }

            val stoperFragment = StoperFragment()

            supportFragmentManager.commit {
                add(R.id.fragmentContainerView2, detailFrag)
                add(R.id.fragmentContainerViewStoper, stoperFragment)
            }
        }
    }
}