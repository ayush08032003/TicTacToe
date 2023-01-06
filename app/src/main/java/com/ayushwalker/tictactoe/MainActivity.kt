package com.ayushwalker.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    var PLAYER:Boolean = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}


    // initalizing it late
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // creating a 2-D array
        board = arrayOf(
            arrayOf(button1,button2, button3),
            arrayOf(button4,button5, button6),
            arrayOf(button7,button8, button9)
        )

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            PlayerTurnDisplayTextView.text = "Player ${if(PLAYER) "X" else "O"} Turn"
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = " "
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.button1->{
                    updateValue(row = 0, col = 0,player = PLAYER)
                }
                R.id.button2->{
                    updateValue(row = 0, col = 1,player = PLAYER)
                }
                R.id.button3->{
                    updateValue(row = 0, col = 2,player = PLAYER)
                }
                R.id.button4->{
                    updateValue(row = 1, col = 0,player = PLAYER)
                }
                R.id.button5->{
                    updateValue(row = 1, col = 1,player = PLAYER)
                }
                R.id.button6->{
                    updateValue(row = 1, col = 2,player = PLAYER)
                }
                R.id.button7->{
                    updateValue(row = 2, col = 0,player = PLAYER)
                }
                R.id.button8->{
                    updateValue(row = 2, col = 1,player = PLAYER)
                }
                R.id.button9->{
                    updateValue(row = 2, col = 2,player = PLAYER)
                }
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        board[row][col].textSize = 30F
        board[row][col].text = if(player) "X" else "O"
        board[row][col].isEnabled = false
        boardStatus[row][col] = if(player) 1 else 0
        PLAYER = !PLAYER
        TURN_COUNT++
//        val next =
        val win = checkWining()

        if(TURN_COUNT == 9 && win == -1){
            PlayerTurnDisplayTextView.text = "GAME DRAW, PRESS RESET TO PLAY AGAIN"
        }else if(win == 1){
            PlayerTurnDisplayTextView.text = "PLAYER X WINS, RESET THE GAME"
            disableAllButtons()
        }
        else if(win == 0){
            PlayerTurnDisplayTextView.text = "PLAYER O WINS, RESET THE GAME"
            disableAllButtons()
        }
        else{
            PlayerTurnDisplayTextView.text = "Player ${if(PLAYER) "X" else "O"} Turn"
        }

    }

    private fun disableAllButtons() {
        for(i in 0..2){
            for(j in 0..2){
                if(board[i][j].isEnabled) board[i][j].isEnabled = false
            }
        }
    }

    private fun checkWining():Int {
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] && boardStatus[i][0] != -1){
                return boardStatus[i][0]
            }
        }

        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i] && boardStatus[0][i] != -1){
                return boardStatus[0][i]
            }
        }

        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2] && boardStatus[0][0] != -1){
            return boardStatus[0][0]
        }

        if(boardStatus[2][0] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[0][2] && boardStatus[2][0] != -1){
            return boardStatus[2][0]
        }


        return -1
    }
}