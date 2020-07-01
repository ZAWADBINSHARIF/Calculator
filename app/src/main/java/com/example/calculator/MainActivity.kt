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
            val isMinus = resultView.text.any { it == '-' }

            resultView.text = backSpace

            if (resultView.text.isEmpty() && !isMinus) {
                showOP = true
                firstMinus = false
            }

            if (isMinus && (resultView.text.toString().length > 1)) {
                showOP = false
                firstMinus = true
            }

            if (resultView.text.isNotEmpty()) {
                if (resultView.text.toString()[resultView.text.toString().length - 1] != '.') {
                    showOP = false
                }
                if (resultView.text.toString()[resultView.text.toString().length - 1] == '.') {
                    showOP = true
                }
            }
        }
    }

    fun onAllClear(view: View) {
        calculationView.text = ""
        resultView.text = ""
        showOP = true
        firstMinus = false
    }

    fun onDot(view: View) {
        val isDot = resultView.text.any { it == '.' }
        val isLastDot = resultView.text.toString()

        if (!isDot && resultView.text.isNotEmpty()) {
            resultView.append((view as Button).text)
            showOP = resultView.text.toString()[resultView.text.toString().length - 1] == '.'
        }

        if (resultView.text.isEmpty()) {
            resultView.text = "0."
            showOP = resultView.text.toString()[resultView.text.toString().length - 1] == '.'
        }
    }

    fun onSin(view: View) {

    }

    fun onCos(view: View) {

    }

    fun onEqual(view: View) {

    }
}
