package com.example.szlaki_turystyczne

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.szlaki_turystyczne.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nazwa = arguments?.getString("nazwa")
        val opis = arguments?.getString("opis")

        binding.nazwaSciezki.text = nazwa
        binding.opisSciezki.text = opis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

