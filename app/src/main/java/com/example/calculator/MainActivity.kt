package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()
    }

    fun onDigit(view: View) {
        calculationView.append((view as Button).text)
    }

    fun onBackSpace(view: View) {
        val calculation = calculationView.text.toString()

        when {
            calculation.isNotEmpty() -> {
                calculationView.text = calculation.substring(0, calculation.length - 1)
            }
        }
    }

    fun onAllClear(view: View) {
        calculationView.text = ""
        resultView.text = ""
    }

    fun onDot(view: View) {

    }

    fun onBracket(view: View) {
        calculationView.append((view as Button).text)
    }
}