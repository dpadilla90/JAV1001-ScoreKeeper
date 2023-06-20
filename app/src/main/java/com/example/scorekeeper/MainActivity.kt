package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var increaseButton1: Button
    private lateinit var increaseButton2: Button
    private lateinit var decreaseButton1: Button
    private lateinit var decreaseButton2: Button
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //'R' is a class in Android that contains the definitions for all the recources
        //of our application. In this. R.id.ture_button -> a button with the id "true_button"
        //that is defined in the layout file(specifically activity_main.xml)

        increaseButton1 = findViewById(R.id.increase_button_t1)
        increaseButton2 = findViewById(R.id.increase_button_t2)
        decreaseButton1 = findViewById(R.id.decrease_button_t1)
        decreaseButton2 = findViewById(R.id.decrease_button_t2)

        increaseButton1.setOnClickListener {
            score++
            updateScoreTextView()
        }
        decreaseButton1.setOnClickListener {
            if (score > 0) {
                score--
            }
            updateScoreTextView()
        }
    }
    private fun updateScoreTextView() {
        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        scoreTextView.text = score.toString()
    }

}