package com.example.szlaki_turystyczne

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.szlaki_turystyczne.databinding.FragmentStoperBinding
import java.util.Locale

class StoperFragment : Fragment() {
    private lateinit var binding: FragmentStoperBinding
    private var seconds = 0
    private var running = false
    private var wasRunning = false
    private var handler: Handler? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds")
            this.running = savedInstanceState.getBoolean("running")
            this.wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        binding = FragmentStoperBinding.inflate(inflater, container, false)
        setupButtons()
        runStoper()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = Handler(Looper.getMainLooper())
        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds")
            this.running = savedInstanceState.getBoolean("running")
        }
        runStoper()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler?.removeCallbacksAndMessages(null)
    }

    private fun setupButtons() {
        binding.startButton.setOnClickListener {
            onClickStart()
        }

        binding.stopButton.setOnClickListener {
            onClickStop()
        }

        binding.resetButton.setOnClickListener {
            onClickReset()
        }
    }

    private fun runStoper() {
        handler?.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                binding.stopwatchTextView.text = time
                if (running) {
                    seconds++
                }
                handler?.postDelayed(this, 1000)
            }
        })
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }
}
