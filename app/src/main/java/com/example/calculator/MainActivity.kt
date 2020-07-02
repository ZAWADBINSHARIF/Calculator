package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var showOP = true
    private var firstMinus = false
    private var num1 : Double? = null
    private var num2 : Double? = 0.0
    private var sResult : Double = 0.0
    private var showResult : Double? = null
    private var storeOP : String? = null
    private var showResultCalculation: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()
        firstMinus = false

    }

    fun onDigit(view: View) {
        showResultCalculation = showResult != null

        if (showResultCalculation == true) {
            scanOP()
            showResult = null
            resultView.text = ""
            resultView.append((view as Button).text)
            showOP = false
            firstMinus = true
        }else {
            resultView.append((view as Button).text)
            showOP = false
            firstMinus = true
        }
    }

    fun onOperator(view: View) {

        val op = (view as Button).text.toString()

        if (!showOP && resultView.text.toString() != "-") {

            scanOP(op)

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

            resultView.text = backSpace

            val isMinus = resultView.text.any { it == '-' }

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
        num1 = null
        num2 = 0.0
        sResult = 0.0
        showResult = 0.0
        storeOP = null
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

    private fun storeResult(sNumber: String) {
        sResult = sNumber.toDouble()
    }

    private fun calculation(storeOP: String) {
        if (num1 != null && num2 != null && this.storeOP != null) {
            when(storeOP) {
                "+" -> {
                    showResult = num1!! + num2!!
                    resultView.text = showResult.toString()
                }
                "-" -> {
                    showResult = num1!! - num2!!
                    resultView.text = showResult.toString()
                }
                "*" -> {
                    showResult = num1!! * num2!!
                    resultView.text = showResult.toString()
                }
                "/" -> {
                    try {
                        showResult = num1!! / num2!!
                    }catch (message: Exception) {
                        println(message)
                    }
                    resultView.text = showResult.toString()
                }
            }
        }
    }

    private fun scanOP(op: String) {
        when(op) {
            "+" -> {
                when {
                    num1 == null && num2 == 0.0 -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        calculationView.append("+\n")
                        resultView.text = ""

                        num1 = sResult
                        num2 = null
                        storeOP = "+"
                    }

                    num2 == null && num1 != null -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        num2 = sResult

                        calculation(storeOP!!)

                        num1 = null
                        num2 = 0.0
                        storeOP = "+"
                        calculationView.append("+\n")
                    }
                }
            }

            "-" -> {
                when {
                    num1 == null && num2 == 0.0 -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        calculationView.append("-\n")
                        resultView.text = ""

                        num1 = sResult
                        num2 = null
                        storeOP = "-"
                    }

                    num2 == null && num1 != null -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        num2 = sResult

                        calculation(storeOP!!)

                        num1 = null
                        num2 = 0.0
                        storeOP = "-"
                        calculationView.append("-\n")
                    }
                }
            }

            "*" -> {
                when {
                    num1 == null && num2 == 0.0 -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        calculationView.append("*\n")
                        resultView.text = ""

                        num1 = sResult
                        num2 = null
                        storeOP = "*"
                    }

                    num2 == null && num1 != null -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        num2 = sResult

                        calculation(storeOP!!)

                        num1 = null
                        num2 = 0.0
                        storeOP = "*"
                        calculationView.append("*\n")
                    }
                }
            }

            "/" -> {
                when {
                    num1 == null && num2 == 0.0 -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        calculationView.append("/\n")
                        resultView.text = ""

                        num1 = sResult
                        num2 = null
                        storeOP = "/"
                    }

                    num2 == null && num1 != null -> {
                        storeResult(resultView.text.toString())
                        calculationView.append("${resultView.text}\n")
                        num2 = sResult

                        calculation(storeOP!!)

                        num1 = null
                        num2 = 0.0
                        storeOP = "/"
                        calculationView.append("/\n")
                    }
                }
            }

        }
    }
}
