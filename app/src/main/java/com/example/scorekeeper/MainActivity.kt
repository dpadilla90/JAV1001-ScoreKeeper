package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Declare private properties for UI elements and scores
    private lateinit var increaseButton1: Button
    private lateinit var increaseButton2: Button
    private lateinit var decreaseButton1: Button
    private lateinit var decreaseButton2: Button
    private lateinit var incrementOptionsRadioGroup: RadioGroup
    private var score1 = 0
    private var score2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change the title text programmatically
        val newTitle = "Scorekeeper App"
        setTitle(newTitle)

        // Initialize UI elements by finding their respective views in the layout
        increaseButton1 = findViewById(R.id.increase_button_t1)
        increaseButton2 = findViewById(R.id.increase_button_t2)
        decreaseButton1 = findViewById(R.id.decrease_button_t1)
        decreaseButton2 = findViewById(R.id.decrease_button_t2)
        incrementOptionsRadioGroup = findViewById(R.id.incrementOptionsRadioGroup)

        // Set click listeners for buttons to handle score updates
        increaseButton1.setOnClickListener {
            // Increase the score of team 1 based on the selected increment value
            score1 += getSelectedIncrementValue()
            updateScoreTextView()
        }
        decreaseButton1.setOnClickListener {
            // Decrease the score of team 1 by 1 if the score is greater than 0
            if (score1 > 0) {
                score1--
            }
            updateScoreTextView()
        }
        increaseButton2.setOnClickListener {
            // Increase the score of team 2 based on the selected increment value
            score2 += getSelectedIncrementValue()
            updateScoreTextView()
        }
        decreaseButton2.setOnClickListener {
            // Decrease the score of team 2 by 1 if the score is greater than 0
            if (score2 > 0) {
                score2--
            }
            updateScoreTextView()
        }
    }

    // Update the score text views with the current scores
    private fun updateScoreTextView() {
        val scoreTextView1: TextView = findViewById(R.id.scoreTextView1)
        scoreTextView1.text = score1.toString()
        val scoreTextView2: TextView = findViewById(R.id.scoreTextView2)
        scoreTextView2.text = score2.toString()
    }

    // Get the selected increment value from the radio button group
    private fun getSelectedIncrementValue(): Int {
        return when (incrementOptionsRadioGroup.checkedRadioButtonId) {
            R.id.option1RadioButton -> 1
            R.id.option2RadioButton -> 2
            R.id.option3RadioButton -> 3
            else -> 0
        }
    }
}
