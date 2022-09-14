package com.ncannone.tictactoe.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ncannone.tictactoe.R

class GameOverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.end_fragment, container, false)
        val button = view.findViewById<Button>(R.id.newGame)
        button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
        return view
    }
}