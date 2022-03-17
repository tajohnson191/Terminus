package com.example.terminus

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.terminus.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //top level variable using in binding that will be used across methods
    //lateinit is a promise that the variable will be initialized before using it
    lateinit var binding: ActivityMainBinding

    //TODO larger list of valid words or an api call
    val validWords = listOf<String>("trade", "juice", "horse","apples")
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
//        binding.submitButton.isVisible = false
//        binding.letter.


        //add a click listener to the challenge button
        binding.challengeButton.setOnClickListener { finalWord() }
    }

    private fun finalWord() {

        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(45)
        binding.letter.filters = filterArray
        binding.submitButton.isVisible = true
//        determineWinner(binding.letter.text.toString())


        /// TODO: new function that allows challenge button presser to type out rest of word that was being spelled
        /// TODO: when submit pressed different submit button function here, calls determineWinner
    }

    private fun determineWinner(word:String){
        if(word.length >= 5) {
            if (checkWord(word)) {
                val loser = if (playerOne) "Trisha" else "Xain"
                outcome = "$word is a valid word! $loser has lost!"

                //show outcome
                binding.outcome.text = outcome

                //clear out existing word
                binding.word.text = null
            }
        } else {
            val loser = if (!playerOne) "Trisha" else "Xain"
            outcome = "$word is not a valid word! $loser has lost!"

            //show outcome
            binding.outcome.text = outcome

            //clear out existing word
            binding.word.text = null
        }
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
//        if (newWord.length > 4) {
//            checkWordDuring(newWord)
//        }


        //switch whose turn it is after the letter is entered.
        determineTurn(false)
    }

//
//    fun checkWordDuring(word: String) {
//        if (word in validWords) {
//            val loser = if (playerOne) "Trisha" else "Xain"
//            outcome = "$word is a valid word! $loser has lost!"
//
//            //show outcome
//            binding.outcome.text = outcome
//
//            //clear out existing word
//            binding.word.text = null
//        }
//
//    }

    fun checkWord(wordToCheck:String):Boolean {
        if (wordToCheck.length < 5) {
            return false
        } else {
            return wordToCheck in validWords
        }
    }

//        //if challenge is hit, let the other player keep typing and then click submit.
//
//        //get the word that is being challenged
//        val wordToCheck = binding.word.text.toString()
//
//
//        //check if the word being challenged is in the list of valid words
//        val outcome: String
//
//        //if word being challenged is at least 5 letters, determine if it's in the valid words
//        if (wordToCheck.length < 4) {
//            val loser = if (playerOne) "Trisha" else "Xain"
//            outcome = "$wordToCheck is too short! $loser loses!"
//        } else {
//            if (wordToCheck in validWords) {
//                outcome = "$wordToCheck is a valid word!" + if (!playerOne) " Trisha wins" else " Xain wins"
//
//            } else
//                outcome = "$wordToCheck is not a valid word!" + if (playerOne) " Trisha loses" else " Xain loses"
//
//        }
//
//        //show the outcome
//        binding.outcome.text = outcome
//
//        //clear out the word being challenged
//        binding.word.text = null

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

    //TODO variables for player one and player two names and change formatting more
    fun player1() {
        binding.playerOne.typeface = Typeface.DEFAULT_BOLD
        binding.playerTwo.typeface = Typeface.DEFAULT
    }

    //TODO variables for player one and player two names and change formatting more
    fun player2() {
        binding.playerOne.typeface = Typeface.DEFAULT
        binding.playerTwo.typeface = Typeface.DEFAULT_BOLD

    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_A,
            KeyEvent.KEYCODE_B,
            KeyEvent.KEYCODE_C,
            KeyEvent.KEYCODE_D,
            KeyEvent.KEYCODE_E,
            KeyEvent.KEYCODE_F,
            KeyEvent.KEYCODE_G,
            KeyEvent.KEYCODE_H,
            KeyEvent.KEYCODE_I,
            KeyEvent.KEYCODE_J,
            KeyEvent.KEYCODE_K,
            KeyEvent.KEYCODE_L,
            KeyEvent.KEYCODE_M,
            KeyEvent.KEYCODE_N,
            KeyEvent.KEYCODE_O,
            KeyEvent.KEYCODE_P,
            KeyEvent.KEYCODE_Q,
            KeyEvent.KEYCODE_R,
            KeyEvent.KEYCODE_S,
            KeyEvent.KEYCODE_T,
            KeyEvent.KEYCODE_U,
            KeyEvent.KEYCODE_V,
            KeyEvent.KEYCODE_W,
            KeyEvent.KEYCODE_X,
            KeyEvent.KEYCODE_Y,
            KeyEvent.KEYCODE_Z,
            -> {

                addToWord()
                println("yo")
                return true
            }

        }
        return super.onKeyUp(keyCode, event)
    }
}
