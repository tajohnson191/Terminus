package com.example.terminus

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.terminus.databinding.ActivityMainBinding
import android.text.Editable
import android.text.TextWatcher


class MainActivity : AppCompatActivity() {

    //top level variable using in binding that will be used across methods
    //lateinit is a promise that the variable will be initialized before using it
    lateinit var binding: ActivityMainBinding

    //TODO larger list of valid words or an api call
    val validWords = listOf<String>("trade", "juice", "horse","apples")
    var outcome: String = ""
    var playerOne = true
    var playerTwo = false

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            addToWord()
        }
    }
    private val textWatcher2: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            addToWord(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializes the binding object which is used to access Views in the layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        //sets the contentView of the activity, instead of passing the resource ID. Specifies the root of the hierarchy of your views
        setContentView(binding.root)

        //set player one as
        determineTurn(true)

        binding.letter.addTextChangedListener(textWatcher)

        //add a click listener to the submit button
        binding.submitButton.setOnClickListener { determineWinner(binding.word.text.toString()) }
        binding.submitButton.isVisible = false
//        binding.letter.


        //add a click listener to the challenge button
        binding.challengeButton.setOnClickListener { finalWord() }
    }
    private fun resetGame(){ //called from new game
        binding.submitButton.isVisible = false
        determineTurn(true)

    }

    private fun finalWord() {
        determineTurn(false)
        binding.letter.removeTextChangedListener(textWatcher)
        binding.letter.addTextChangedListener(textWatcher2)
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(45)
        binding.letter.filters = filterArray
        binding.submitButton.isVisible = true
        addToWord(true)
//        determineWinner(binding.letter.text.toString())
        /// TODO: new function that allows challenge button presser to type out rest of word that was being spelled
        /// TODO: when submit pressed different submit button function here, calls determineWinner
    }

    private fun determineWinner(word:String){
        if(word.length >= 5) {
            println(checkWord(word).toString())
            if (checkWord(word)) {
                val loser = if (playerOne) "Trisha" else "Xain"
                outcome = "$word is a valid word! $loser has lost!"

                //show outcome
                binding.outcome.text = outcome

                //clear out existing word
                binding.word.text = null
            } else {
                val loser = if (!playerOne) "Trisha" else "Xain"
                outcome = "$word is not a valid word! $loser has lost!"

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
    fun addToWord(isChallenge : Boolean){//get the existing word
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
        binding.letter.removeTextChangedListener(textWatcher2)
        binding.letter.text = null
        binding.letter.addTextChangedListener(textWatcher2)

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
        binding.letter.removeTextChangedListener(textWatcher)
        binding.letter.text = null
        binding.letter.addTextChangedListener(textWatcher)

        if(checkWord(newWord) && newWord.length>= 5) {
            determineWinner(newWord)
        }

        //switch whose turn it is after the letter is entered.
        determineTurn(false)
    }


    fun checkWord(wordToCheck:String):Boolean {
        if (wordToCheck.length < 5) {
            return false
        } else {
            return wordToCheck in validWords
        }
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
}
