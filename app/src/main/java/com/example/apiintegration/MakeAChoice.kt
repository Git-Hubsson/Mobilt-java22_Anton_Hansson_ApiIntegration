package com.example.apiintegration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class MakeAChoice : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_make_a_choice, container, false)

        val weatherButton: Button = view.findViewById(R.id.weatherButton)
        weatherButton.setOnClickListener{
            Navigation.findNavController(requireView())
                .navigate(R.id.action_makeAChoice_to_weather)
        }

        val jokeButton: Button = view.findViewById(R.id.italianJokeButton)
        jokeButton.setOnClickListener{
            Navigation.findNavController(requireView())
                .navigate(R.id.action_makeAChoice_to_italianJoke)
        }

        return view
    }
}