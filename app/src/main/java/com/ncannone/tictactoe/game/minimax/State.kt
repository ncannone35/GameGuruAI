package com.ncannone.tictactoe.game.minimax

class State {
    var x = 0
    var y = 0
    var score = 0

    constructor(x: Int, y: Int, score: Int) {
        this.x = x
        this.y = y
        this.score = score
    }

    constructor() {}
}