package com.example.apiintegration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Weather : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        requestQueue = Volley.newRequestQueue(requireContext())

        val searchButton: Button = view.findViewById(R.id.searchButton)
        searchButton.setOnClickListener() {
            val searchInput: String = view.findViewById<EditText>(R.id.searchBar).text.toString()
            val apiKey = "d63ffbe45b3262ed4ed9578f4260f9a0"
            val url =
                "https://api.openweathermap.org/data/2.5/weather?q=${searchInput}&appid=${apiKey}&units=metric"

            val j2 = JsonObjectRequest(
                url,
                { res ->
                    var mainWeather = res.getJSONObject("main");
                    var descWeather = res.getJSONArray("weather");
                    var description = descWeather.getJSONObject(0);
                    var temperature: String = mainWeather.getString("temp");
                    var condition = description.getString("description");
                    val currentWeather: TextView = view.findViewById(R.id.weatherPresentation)
                    currentWeather.setText("The current weather in ${searchInput} is ${temperature} degrees and ${condition}.")
                }, { err -> Log.d("Anton", err.toString()) })

            requestQueue.add(j2)
        }

        return view
    }
}