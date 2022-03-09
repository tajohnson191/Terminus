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
        //get the letter from the letter input field.
        val stringInTextField = binding.letter.text.toString()

        //get the existing word
        val existingWord = binding.word.text.toString()

        //append the new letter to the existing word
        var newWord = StringBuilder(existingWord).append(stringInTextField).toString()

        //show the new word
        binding.word.text = newWord

        //clear out the existing letter after it's appended
        binding.letter.text = null
}
    fun checkWord() {
        val wordToCheck = binding.word.text.toString()

        val validWords = listOf<String>("jest", "lest", "mess")

        if (wordToCheck in validWords) {
            println("Valid word!")
        } else
            println("Not valid word!")


    }
}
