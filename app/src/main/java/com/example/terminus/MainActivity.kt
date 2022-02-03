package com.example.terminus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var nextActivityButton : Button? = null
    var question1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextActivityButton = findViewById<View>(R.id.button) as Button
        question1 = findViewById<View>(R.id.textView2) as TextView
        question1!!.text = "ASDF".trimIndent()

        nextActivityButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, HomePage::class.java)
            startActivity(intent)
        }
    }
}
