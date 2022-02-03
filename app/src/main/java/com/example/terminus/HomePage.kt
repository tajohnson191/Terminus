package com.example.terminus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import android.widget.TextView


class HomePage : AppCompatActivity() {
    var nextActivityButton : Button? = null
    var question1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        nextActivityButton = findViewById<View>(R.id.button4) as Button
        question1 = findViewById<View>(R.id.textView3) as TextView
        question1!!.text = "ASDF".trimIndent()

        nextActivityButton!!.setOnClickListener {
            val intent = Intent(this@HomePage, MainActivity::class.java)
            startActivity(intent)
        }


    }
}