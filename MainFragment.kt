package com.example.szlaki_turystyczne

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.szlaki_turystyczne.databinding.FragmentMainBinding
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    //private lateinit var detailFragment: DetailFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //detailFragment = DetailFragment()

        val jsonString = loadJSONFromAsset(requireContext(), "sciezki.json")
        val pathNames = parseJSON(jsonString)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, pathNames)
        binding.listview.adapter = adapter

        binding.listview.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val selectedPath = pathNames[position]
            val selectedPathDetails = getPathDetails(jsonString, position)
            Toast.makeText(requireContext(), "Wybrana ścieżka $selectedItem", Toast.LENGTH_SHORT).show()
            startDetailActivity(selectedPathDetails)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadJSONFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    private fun parseJSON(jsonString: String?): ArrayList<String> {
        val pathNames = ArrayList<String>()
        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val name = jsonObject.getString("nazwa")
                pathNames.add(name)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return pathNames
    }

    private fun getPathDetails(jsonString: String?, position: Int): Pair<String, String> {
        var nazwa = ""
        var opis = ""
        try {
            val jsonArray = JSONArray(jsonString)
            val jsonObject = jsonArray.getJSONObject(position)
            nazwa = jsonObject.getString("nazwa")
            opis = jsonObject.getString("opis")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return Pair(nazwa, opis)
    }

    private fun startDetailActivity(selectedPathDetails: Pair<String, String>) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("nazwa", selectedPathDetails.first)
            putExtra("opis", selectedPathDetails.second)
        }
        startActivity(intent)
    }
}