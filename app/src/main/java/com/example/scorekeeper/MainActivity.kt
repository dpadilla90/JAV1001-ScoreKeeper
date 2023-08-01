package com.example.scorekeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scorekeeper.databinding.ActivityMainBinding
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    // Declare private properties for UI elements and scores
    private lateinit var binding: ActivityMainBinding
    private var score1 = 0
    private var score2 = 0
    private var saveScores = true
    // Declare the property for selected radio button ID
    private var selectedRadioButtonId = R.id.option1RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up the custom Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Restore scores and settings from SharedPreferences
        val sharedPreferences = getSharedPreferences("ScoreKeeperPrefs", MODE_PRIVATE)
        score1 = sharedPreferences.getInt(KEY_SCORE_1, 0)
        score2 = sharedPreferences.getInt(KEY_SCORE_2, 0)
        saveScores = sharedPreferences.getBoolean(KEY_SAVE_SCORES, true)
        selectedRadioButtonId = sharedPreferences.getInt(KEY_SELECTED_RADIO_BUTTON, R.id.option1RadioButton)


        // Set the selected radio button
        binding.incrementOptionsRadioGroup.check(selectedRadioButtonId)

        // Set click listeners for radio buttons to handle selection changes
        binding.option1RadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedRadioButtonId = R.id.option1RadioButton
                saveSelectedRadioButtonToSharedPreferences(selectedRadioButtonId)
            }
        }

        binding.option2RadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedRadioButtonId = R.id.option2RadioButton
                saveSelectedRadioButtonToSharedPreferences(selectedRadioButtonId)
            }
        }

        binding.option3RadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedRadioButtonId = R.id.option3RadioButton
                saveSelectedRadioButtonToSharedPreferences(selectedRadioButtonId)
            }
        }

        // Check if there is saved state to restore the scores
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt(KEY_SCORE_1, 0)
            score2 = savedInstanceState.getInt(KEY_SCORE_2, 0)
            saveScores = savedInstanceState.getBoolean(KEY_SAVE_SCORES, true)
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
        // Save scores and settings whenever scores are updated
        saveScoresToSharedPreferences()
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

    // Save the scores and settings to the bundle before rotation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SCORE_1, score1)
        outState.putInt(KEY_SCORE_2, score2)
        outState.putBoolean(KEY_SAVE_SCORES, saveScores)
    }

    // Create the menu and handle menu item clicks
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // Show developer information in a Toast
                showToast(getString(R.string.developer_info))
                return true
            }

            R.id.action_settings -> {
                // Open SettingsActivity to manage settings
                openSettingsActivity()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Helper function to show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    // Function to open SettingsActivity
    private fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.EXTRA_SAVE_SCORES, saveScores)
        startActivityForResult(intent, REQUEST_SETTINGS)
    }

    // Receive the result from SettingsActivity and handle it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SETTINGS && resultCode == Activity.RESULT_OK) {
            val saveScoresResult = data?.getBooleanExtra(SettingsActivity.EXTRA_SAVE_SCORES, true) ?: true
            if (saveScores != saveScoresResult) {
                saveScores = saveScoresResult
                // Save scores and settings whenever scores are updated
                saveScoresToSharedPreferences()
            }
        }
    }

    private fun saveScoresToSharedPreferences() {
        val sharedPreferences = getSharedPreferences("ScoreKeeperPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_SCORE_1, score1)
        editor.putInt(KEY_SCORE_2, score2)
        editor.putBoolean(KEY_SAVE_SCORES, saveScores)
        editor.apply()
    }

    private fun saveSelectedRadioButtonToSharedPreferences(selectedRadioButtonId: Int) {
        val sharedPreferences = getSharedPreferences("ScoreKeeperPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_SELECTED_RADIO_BUTTON, selectedRadioButtonId)
        editor.apply()
    }


    companion object {
        private const val KEY_SCORE_1 = "score1"
        private const val KEY_SCORE_2 = "score2"
        private const val KEY_SAVE_SCORES = "save_scores"
        private const val KEY_SELECTED_RADIO_BUTTON = "selected_radio_button"
        private const val REQUEST_SETTINGS = 1
    }
}
