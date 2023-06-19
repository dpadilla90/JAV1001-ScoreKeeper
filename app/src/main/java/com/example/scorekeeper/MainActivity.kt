package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //'R' is a class in Android that contains the definitions for all the recources
        //of our application. In this. R.id.ture_button -> a button with the id "true_button"
        //that is defined in the layout file(specifically activity_main.xml)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        falseButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}