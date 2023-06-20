package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var increaseButton1: Button
    private lateinit var increaseButton2: Button
    private lateinit var decreaseButton1: Button
    private lateinit var decreaseButton2: Button
    private var score1 = 0
    private var score2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //'R' is a class in Android that contains the definitions for all the resources
        //of our application. In this. R.id.ture_button -> a button with the id "true_button"
        //that is defined in the layout file(specifically activity_main.xml)

        increaseButton1 = findViewById(R.id.increase_button_t1)
        increaseButton2 = findViewById(R.id.increase_button_t2)
        decreaseButton1 = findViewById(R.id.decrease_button_t1)
        decreaseButton2 = findViewById(R.id.decrease_button_t2)

        increaseButton1.setOnClickListener {
            score1++
            updateScoreTextView()
        }
        decreaseButton1.setOnClickListener {
            if (score1> 0) {
                score1--
            }
            updateScoreTextView()
        }
        increaseButton2.setOnClickListener {
            score2++
            updateScoreTextView()
        }
        decreaseButton2.setOnClickListener {
            if (score2 > 0) {
                score2--
            }
            updateScoreTextView()
        }
    }
    private fun updateScoreTextView() {
        val scoreTextView1: TextView = findViewById(R.id.scoreTextView1)
        scoreTextView1.text = score1.toString()
        val scoreTextView2: TextView = findViewById(R.id.scoreTextView2)
        scoreTextView2.text = score2.toString()
    }

}