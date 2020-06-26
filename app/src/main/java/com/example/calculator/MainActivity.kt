package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1 = 0.0
    private var num2 = 0.0
    private var sResult = 0.0
    var sNum1 = false
    var sNum2 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sNum1 = false
        var sNum2 = true

        calculationView.movementMethod = ScrollingMovementMethod()
    }

    fun onDigit(view: View) {
        resultView.text = ""
        resultView.append((view as Button).text)
    }

    fun onOperator(view: View) {
        val numberShow = resultView.text.toString()


        if (!sNum1) {
            storeOpText.text =(view as Button).text
            num1  = numberShow.toDouble()
            calculationView.append("$numberShow${storeOpText.text}")
            resultView.text = ""
            sNum1 = true
            sNum2 = false
        }
        if (!sNum2) {
            num2  = numberShow.toDouble()
            calculationView.append("$numberShow${storeOpText.text}")
            storeOP(resultView)
            storeOpText.text =(view as Button).text
            sNum1 = false
            sNum2 = true
        }

    }

    fun onBackSpace(view: View) {
        val backSpace = resultView.text.substring(0, resultView.text.length-1)

        resultView.text = backSpace
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
        resultView.text = sResult.toString()
        calculationView.text = ""
    }

    private fun storeOP(showResult : TextView) {
        var result = sResult
        if (!storeOpText.equals("")) {
        when(storeOpText.text) {
            "+" -> {
                result = num1.plus(num2)
                showResult.text = "$result"
            }
            "-" -> {
                result = num1.minus(num2)
                showResult.text = "$result"
            }
            "*" -> {
                result = num1.times(num2)
                showResult.text = "$result"
            }
            "/" -> {
                result = num1.div(num2)
                showResult.text = "$result"
            }
        }
        }
    }

}