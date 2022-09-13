package com.ncannone.tictactoe.game.minimax

import android.widget.TextView
import com.ncannone.tictactoe.game.minimax.Minimax

class Minimax(currBoard: List<TextView>) {
    var player = 1
    var computer = -1
    private val board = Array(3) { IntArray(3) } //initial state
    private val currBoard = currBoard

    fun loadBoard() {
        for (i in 0..8) {
            val x = i/3
            val y = i%3
            if(currBoard[i].text == "X") {
                board[x][y] = 1
            }
            else if (currBoard[i].text == "O") {
                board[x][y] = -1
            }
            else board[x][y] = 0
        }
    }

    fun rowWin(): Boolean { //checks for a row win
        for (i in 0..2) {
            if (board[i][0] != 0) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true
            }
        }
        return false
    }

    fun colWin(): Boolean { //checks for a col win
        for (i in 0..2) {
            if (board[0][i] != 0) {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true
            }
        }
        return false
    }

    fun diagWin(): Boolean { //checks for a diagonal win
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true
        else return (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
    }

    //checks if board is a winner, GOAL STATE
    val isWin: Boolean
        get() =//checks if board is a winner, GOAL STATE
            diagWin() || rowWin() || colWin()

    //checks for ties, GOAL STATE
    val isTie: Boolean
        get() { //checks for ties, GOAL STATE
            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j] == 0) return false
                }
            }
            return true
        }

    fun minimax_move(): Int {
        val best = minimax(true)
        board[best.x][best.y] = computer
        return 3 * (best.x) + (best.y)
    }

    fun minimax(max: Boolean): State {
        val best = State()
        if (isWin) { //checks for wins
            if (max) {
                best.score = -1
            } else best.score = 1
            return best
        } else if (isTie) { //checks for ties
            best.score = 0
            return best
        }
        if (max) best.score = Int.MIN_VALUE else best.score = Int.MAX_VALUE
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == 0) { //loops through every state in the state space
                    if (max) board[i][j] = computer else board[i][j] = player
                    val curr = minimax(!max) //switches MAX and MIN
                    if (max) {
                        if (curr.score > best.score) { //maximizes utility
                            best.score = curr.score
                            best.x = i
                            best.y = j
                        }
                    } else {
                        if (curr.score < best.score) { //minimizes utility
                            best.score = curr.score
                            best.x = i
                            best.y = j
                        }
                    }
                    board[i][j] = 0
                }
            }
        }
        return best
    }
}