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
    private var sResult : Double? = null
    private var showResult : Double? = null
    private var storeOP : String? = null
    private var showResultCalculation: Boolean? = null
    private var showEqual = false
    private var newCalculationNum = 1
    private val statCalculationText = "\n__________________\nStart Calculation ${newCalculationNum}\n__________________\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()
        firstMinus = false

    }

    fun onDigit(view: View) {

        if (showResultCalculation == true){
            resultView.text = ""
            showResultCalculation = false
        }

        resultView.append((view as Button).text)
        showOP = false
        firstMinus = true

    }

    fun onOperator(view: View) {

        val op = (view as Button).text.toString()

        if (showEqual) {

            num1 = null
            num2 = 0.0
            sResult = null
            showResult = null
            storeOP = null
            showResultCalculation = null
            showEqual = false

            calculationView.append("\n__________________\nNew Calculation ${++newCalculationNum}\n__________________\n\n")

            when {
                (!showOP && resultView.text.toString() != "-") -> {

                    scanOP(op)

                    showOP = true
                    firstMinus = false
                }
                (op == "-" && !firstMinus && resultView.text.isEmpty()) -> {
                    resultView.append("${(view as Button).text}")
                    showOP = true
                    firstMinus = true
                }
            }

        } else {

            when {
                (!showOP && resultView.text.toString() != "-") -> {

                    scanOP(op)

                    showOP = true
                    firstMinus = false
                }
                (op == "-" && !firstMinus && resultView.text.isEmpty()) -> {
                    resultView.append("${(view as Button).text}")
                    showOP = true
                    firstMinus = true
                }
            }
        }
    }

    fun onBackSpace(view: View) {

        view.setOnLongClickListener {
            resultView.text = ""
            showOP = true
            firstMinus = false
            true
        }

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
        newCalculationNum = 1
        calculationView.text = ""
        resultView.text = ""
        showOP = true
        firstMinus = false
        num1 = null
        num2 = 0.0
        sResult = null
        showResult = null
        storeOP = null
        showEqual = false
        showResultCalculation = null
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

        if (showResult != null) {
        val showLastChar = calculationView.text.toString().length - 2
        val removeLastSymbol = calculationView.text.toString()[showLastChar]
        val removeDot = showResult!!.toString().endsWith(".0")
        val newShowResult = showResult!!.toString().substring(0, showResult.toString().length - 2)

        if(
            (
                    removeLastSymbol == '+' ||
                    removeLastSymbol == '-' ||
                    removeLastSymbol == '*' ||
                    removeLastSymbol == '/'
                    ) &&
                !showEqual &&
                showResultCalculation == true)
        {
            calculationView.text = calculationView.text.substring(0, showLastChar)
            if (removeDot) {
                calculationView.append("__________________\n$newShowResult\n")
            }else {
                calculationView.append("__________________\n$showResult\n")
            }
            showEqual = true
        }
        }

        if (
            (showResultCalculation == false || showResultCalculation == null) &&
            num2 == null &&
            num1 != null &&
            !showEqual &&
            resultView.text.isNotEmpty()
        ) {
            storeResult(resultView.text.toString())
            calculationView.append("${resultView.text}\n")
            num2 = sResult

            calculation(storeOP!!)

            num1 = null
            num2 = 0.0

            val removeDot = showResult!!.toString().endsWith(".0")
            val newShowResult = showResult!!.toString().substring(0, showResult.toString().length - 2)

            if (removeDot) {
                calculationView.append("__________________\n$newShowResult\n")
                resultView.text = newShowResult
            }else {
                calculationView.append("__________________\n$showResult\n")
                resultView.text = showResult.toString()
            }

            showEqual = true
        }

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
            showResultCalculation = true
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

                        num1 = showResult!!
                        num2 = null
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

                        num1 = showResult!!
                        num2 = null
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

                        num1 = showResult!!
                        num2 = null
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

                        num1 = showResult!!
                        num2 = null
                        storeOP = "/"
                        calculationView.append("/\n")
                    }
                }
            }
        }
    }

}
