package com.ncannone.tictactoe.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.ncannone.tictactoe.R
import com.ncannone.tictactoe.game.minimax.Minimax

class GameFragment : Fragment() {

    private lateinit var board: List<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        board = initializeBoard(view)

        val newGame = view.findViewById<Button>(R.id.restart_button)
        newGame.apply {
            setOnClickListener {
                restartBoard()
            }
        }

        for(i in 0..8) {
            board[i].setOnClickListener {
                if(board[i].text != "X" && board[i].text != "O") {
                    board[i].text = "X"
                    val minimax = Minimax(board)
                    if(minimax.isWin || minimax.isTie) {
                        Navigation.findNavController(view).navigate(R.id.action_gameFragment_to_gameOverFragment)
                    }
                    minimax.loadBoard()
                    val box = minimax.minimax_move()
                    board[box].text = "O"
                    if(minimax.isWin || minimax.isTie) {
                        Navigation.findNavController(view).navigate(R.id.action_gameFragment_to_gameOverFragment)
                    }
                }
            }
        }
        return view
        // Inflate the layout for this fragment
    }

    private fun initializeBoard(view: View) : List<TextView> {
        val board = mutableListOf<TextView>()

        for (i in 1..9) {
            val resId = resources.getIdentifier("box_$i", "id", view.context.packageName)
            val tv = view.findViewById<TextView>(resId)
            board.add(tv)
        }
        return board
    }

    private fun restartBoard() {
        board.map {
            it.text = ""
        }
    }
}