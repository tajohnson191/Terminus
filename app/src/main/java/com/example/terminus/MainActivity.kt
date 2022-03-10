package com.example.terminus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.terminus.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //top level variable using in binding that will be used across methods
    //lateinit is a promise that the variable will be initialized before using it
    lateinit var binding: ActivityMainBinding

    //TODO larger list of valid words or an api call
    val validWords = listOf<String>("trade", "juice", "horse")
    var outcome: String = ""
    var playerOne = true
    var playerTwo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializes the binding object which is used to access Views in the layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        //sets the contentView of the activity, instead of passing the resource ID. Specifies the root of the hierarchy of your views
        setContentView(binding.root)

        //set player one as
        determineTurn(true)

        //add a click listener to the submit button
        binding.submitButton.setOnClickListener { addToWord() }

        //add a click listener to the challenge button
        binding.challengeButton.setOnClickListener { checkWord() }
    }

    fun addToWord() {
        //get the existing word
        val existingWord = binding.word.text.toString()

        //clear out the prior outcome when starting a new word
        if (existingWord == "") {
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

        //determine if the word entered is at least 5 characters and a word
        if (newWord.length > 4) {
            checkWordDuring(newWord)
        }

        //switch whose turn it is after the letter is entered.
        determineTurn(false)
    }

    fun checkWordDuring(word: String) {
        if (word in validWords) {
            val loser = if (playerOne) "Trisha" else "Xain"
            outcome = "$word is a valid word!  $loser has lost!"

            //show outcome
            binding.outcome.text = outcome

            //clear out existing word
            binding.word.text = null
        }

    }

    fun checkWord() {

        //get the word that is being challenged
        val wordToCheck = binding.word.text.toString()


        //check if the word being challenged is in the list of valid words
        val outcome: String

        //if word being challenged is at least 5 letters, determine if it's in the valid words
        if (wordToCheck.length < 4) {
            outcome = "$wordToCheck is too short!"
        } else {
            if (wordToCheck in validWords) {
                outcome = "$wordToCheck is a valid word!"
            } else
                outcome = "$wordToCheck is not a valid word!"
        }

        //show the outcome
        binding.outcome.text = outcome

        //clear out the word being challenged
        binding.word.text = null
    }

    //determine whose turn it should be
    fun determineTurn(firstTime: Boolean) {

        if (firstTime) {
            player1()
        } else {
            if (playerOne) {
                playerOne = false
                playerTwo = true
                player2()
            } else {
                playerOne = true
                playerTwo = false
                player1()
            }
        }
    }

    //TODO variables for player one and player two names
    fun player1() {
        binding.playerOne.text = "It's Trisha's turn"
        binding.playerTwo.text = "It's not Xain's turn"
    }

    //TODO variables for player one and player two names
    fun player2() {
        binding.playerOne.text = "It's not Trisha's turn"
        binding.playerTwo.text = "It's Xain's's turn"

    }
}
