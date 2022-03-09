package com.example.terminus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.terminus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //top level variable using in binding that will be used across methods
    //lateinit is a promise that the variable will be initialized before using it
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializes the binding object which is used to access Views in the layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        //sets the contentView of the activity, instead of passing the resource ID. Specifies the root of the hierarchy of your views
        setContentView(binding.root)

        //add a click listener to the submit button
        binding.submitButton.setOnClickListener{ addToWord() }

        //add a click listener to the challenge button
        binding.challengeButton.setOnClickListener{ checkWord()}
    }

    fun addToWord() {
        //get the existing word
        val existingWord = binding.word.text.toString()

        //clear out the prior outcome when starting a new word
        if(existingWord == "") {
            binding.outcome.text = null
        }

        //get the letter from the letter input field.
        val stringInTextField = binding.letter.text.toString()

        //append the new letter to the existing word
        var newWord = StringBuilder(existingWord).append(stringInTextField).toString()

        //show the new word
        binding.word.text = newWord

        //clear out the existing letter after it's appended
        binding.letter.text = null
}
    fun checkWord() {
        //get the word that is being challenged
        val wordToCheck = binding.word.text.toString()

        //TODO larger list of valid words or an api call
        val validWords = listOf<String>("jest", "lest", "mess")

        //check if the word being challenged is in the list of valid words
        val outcome : String
        if (wordToCheck in validWords) {
            outcome = "$wordToCheck is a valid word!"
        } else
            outcome = "$wordToCheck is not a valid word!"

        //show the outcome
        binding.outcome.text = outcome

        //clear out the word being challenged
        binding.word.text = null
    }
}
