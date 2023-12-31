package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scorekeeper.databinding.ActivitySettingsBinding

// SettingsActivity is used to manage settings related to saving scores and other preferences

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the custom Toolbar
        setSupportActionBar(binding.toolbarSettings)

        // Show the back button in the Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the value of saveScores passed from MainActivity
        val saveScores = intent.getBooleanExtra(EXTRA_SAVE_SCORES, true)

        // Set the initial state of the switch based on the saved value
        binding.saveScoresSwitch.isChecked = saveScores

        // Set a listener to handle changes to the switch state
        binding.saveScoresSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the state of saveScores to be sent back to MainActivity
            val resultIntent = intent.apply {
                putExtra(EXTRA_SAVE_SCORES, isChecked)
            }
            setResult(RESULT_OK, resultIntent)
        }
    }

    // Handle back button click in the Toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        // Constant used as a key to pass data between activities
        const val EXTRA_SAVE_SCORES = "extra_save_scores"
    }
}
