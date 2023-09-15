package com.example.apiintegration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ItalianJoke : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_italian_joke, container, false)

        requestQueue = Volley.newRequestQueue(requireContext())

        val theJoke: TextView = view.findViewById(R.id.theJoke)
        val url = "https://italian-jokes.vercel.app/api/jokes"

        val jsObjReq = JsonObjectRequest(
            url,
            { res ->
                var joke = res.get("joke").toString()
                theJoke.setText(joke)
            },
            { err -> Log.d("Anton", err.toString()) })
        requestQueue.add(jsObjReq)

        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            findNavController().navigate(R.id.action_italianJoke_to_loginFragment)
        }

        return view

    }

}

