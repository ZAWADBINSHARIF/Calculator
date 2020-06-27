package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var num1: Double? = null
    private var num2: Double? = 0.0
    var showResult = ""
    var sResult = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()

        sResult = false
    }

    fun onDigit(view: View) {

        val numbers = resultView.text.toString()
        if (!sResult) {
            resultView.append((view as Button).text)
        } else {
            when (num1) {
                null -> {
                    if (resultView.text.isNotEmpty() && storeOpText.text.isNotEmpty()) {
                        calculationView.append("${resultView.text}${storeOpText.text}")
                    }

                    num1 = numbers.toDouble()
                    num2 = null
                    resultView.text = ""
                }
            }
            resultView.text = ""
            resultView.append((view as Button).text)
            sResult = false
        }
    }

    fun onOperator(view: View) {

        val numbers = resultView.text.toString()

        when {
            num1 == null -> {
                storeOpText.text = (view as Button).text

                if (resultView.text.isNotEmpty() && storeOpText.text.isNotEmpty()) {
                    calculationView.append("${resultView.text}${storeOpText.text}")
                }

                num1 = numbers.toDouble()
                num2 = null
                resultView.text = ""
            }

            num2 == null -> {
                num2 = numbers.toDouble()
                if (storeOpText.text == "+" ||
                    storeOpText.text == "-" ||
                    storeOpText.text == "*" ||
                    storeOpText.text == "/"
                ) {

                    if (num1 !== null && num2 !== null) {

                        if (resultView.text.isNotEmpty() && storeOpText.text.isNotEmpty()) {
                            calculationView.append("${resultView.text}${storeOpText.text}")
                        }
                        val op = storeOpText.text
                        when (op) {

                            "+" -> {
                                showResult = "${num1!! + num2!!}"
                                resultView.text = showResult
                                storeOpText.text = (view as Button).text
                                num1 = null
                                num2 = 0.0
                                sResult = true
                            }
                            "-" -> {
                                showResult = "${num1!! - num2!!}"
                                resultView.text = showResult
                                storeOpText.text = (view as Button).text
                                num1 = null
                                num2 = 0.0
                                sResult = true
                            }
                            "*" -> {
                                showResult = "${num1!! * num2!!}"
                                resultView.text = showResult
                                storeOpText.text = (view as Button).text
                                num1 = null
                                num2 = 0.0
                                sResult = true
                            }
                            "/" -> {
                                try {
                                    showResult = "${num1!! / num2!!}"
                                } catch (message: Exception) {
                                    println(message)
                                }
                                resultView.text = showResult
                                storeOpText.text = (view as Button).text
                                num1 = null
                                num2 = 0.0
                                sResult = true
                            }

                        }
                    }
                }
            }
        }
    }

    fun onBackSpace(view: View) {
        if (resultView.text.isNotEmpty()) {
            val backSpace = resultView.text.substring(0, resultView.text.length - 1)
            resultView.text = backSpace
        }
    }

    fun onAllClear(view: View) {
        calculationView.text = ""
        resultView.text = ""
        storeOpText.text = ""
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