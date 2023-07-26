package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scorekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declare private properties for UI elements and scores
    private lateinit var binding: ActivityMainBinding
    private var score1 = 0
    private var score2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Change the title text programmatically
        val newTitle = "Scorekeeper App"
        supportActionBar?.title = newTitle

        // Check if there is saved state to restore the scores
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt(KEY_SCORE_1, 0)
            score2 = savedInstanceState.getInt(KEY_SCORE_2, 0)
        }

        // Set click listeners for buttons to handle score updates
        binding.increaseButtonT1.setOnClickListener {
            // Increase the score of team 1 based on the selected increment value
            score1 += getSelectedIncrementValue()
            updateScoreTextView()
        }

        binding.increaseButtonT2.setOnClickListener {
            // Increase the score of team 2 based on the selected increment value
            score2 += getSelectedIncrementValue()
            updateScoreTextView()
        }

        binding.decreaseButtonT1.setOnClickListener {
            // Decrease the score of team 1 by 1 if the score is greater than 0
            if (score1 > 0) {
                score1--
            }
            updateScoreTextView()
        }

        binding.decreaseButtonT2.setOnClickListener {
            // Decrease the score of team 2 by 1 if the score is greater than 0
            if (score2 > 0) {
                score2--
            }
            updateScoreTextView()
        }

        // Update the score text views with the current scores
        updateScoreTextView()
    }

    // Update the score text views with the current scores
    private fun updateScoreTextView() {
        binding.scoreTextView1.text = score1.toString()
        binding.scoreTextView2.text = score2.toString()
    }

    // Get the selected increment value from the radio button group
    private fun getSelectedIncrementValue(): Int {
        return when (binding.incrementOptionsRadioGroup.checkedRadioButtonId) {
            R.id.option1RadioButton -> 1
            R.id.option2RadioButton -> 2
            R.id.option3RadioButton -> 3
            else -> 0
        }
    }

    // Save the scores to the bundle before rotation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SCORE_1, score1)
        outState.putInt(KEY_SCORE_2, score2)
    }

    companion object {
        private const val KEY_SCORE_1 = "score1"
        private const val KEY_SCORE_2 = "score2"
    }
}
