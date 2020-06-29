package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var showOP = true
    private var firstMinus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()
        firstMinus = false
    }

    fun onDigit(view: View) {
        resultView.append((view as Button).text)
        showOP = false
        firstMinus = true
    }

    fun onOperator(view: View) {

        val op = (view as Button).text.toString()

        if (!showOP) {
            calculationView.append("${(view as Button).text}\n")
            showOP = true
            }
        if (op == "-" && !firstMinus && resultView.text.isEmpty()) {
            resultView.append("${(view as Button).text}")
            showOP = true
            firstMinus = true
        }
    }

    fun onBackSpace(view: View) {
        if (resultView.text.isNotEmpty()) {
            val backSpace = resultView.text.substring(0, resultView.text.length - 1)
            val isMinus = resultView.text[0]
            resultView.text = backSpace

            firstMinus = isMinus == '-'

            showOP = isMinus == '-' && resultView.text.toString().length == 1
        }
    }

    fun onAllClear(view: View) {
        calculationView.text = ""
        resultView.text = ""
        showOP = true
        firstMinus = false
    }

    fun onDot(view: View) {

    }

    fun onSin(view: View) {

    }

    fun onCos(view: View) {

    }

    fun onEqual(view: View) {

    }
}
