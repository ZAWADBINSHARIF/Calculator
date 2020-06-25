package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var num1 = 0.0
    var num2 = 0.0
    var sResult = 0.0
    private var sNum1 by Delegates.notNull<Boolean>()
    private var sNum2 by Delegates.notNull<Boolean>()
    var sOp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sNum1 = false
        var sNum2 = true
    }

    fun onDigit(view: View) {
        resultView.append((view as Button).text)
    }

    fun onOperator(view: View) {
        val op = (view as Button).text
        val numberShow = resultView.text.toString()

        if (!sNum1) {
            when(op) {
                "+" -> {
                    num1 = numberShow.toDouble()
                    calculationView.append("$numberShow+")
                    resultView.text = ""
                    sNum1 = true
                    sNum2 = false
                    sOp = "+"
                }
                "-" -> {
                    num1 = numberShow.toDouble()
                    calculationView.append("$numberShow-")
                    resultView.text = ""
                    sNum1 = true
                    sNum1 = true
                    sNum2 = false
                    sOp = "-"
                }
                "*" -> {
                    num1 = numberShow.toDouble()
                    calculationView.append("$numberShow*")
                    resultView.text = ""
                    sNum1 = true
                    sNum1 = true
                    sNum2 = false
                    sOp = "*"
                }
                "/" -> {
                    num1 = numberShow.toDouble()
                    calculationView.append("$numberShow/")
                    resultView.text = ""
                    sNum1 = true
                    sNum1 = true
                    sNum2 = false
                    sOp = "/"
                }
            }
        }

        if (!sNum2) {
            when(op) {
                "+" -> {
                    calculationView.append("$numberShow+")
                    num2 = numberShow.toDouble()
                    storeOP(resultView)
                }
                "-" -> {
                    calculationView.append("$numberShow-")
                    num2 = numberShow.toDouble()
                    storeOP(resultView)
                }
                "*" -> {
                    calculationView.append("$numberShow*")
                    num2 = numberShow.toDouble()
                    storeOP(resultView)
                }
                "/" -> {
                    calculationView.append("$numberShow/")
                    num2 = numberShow.toDouble()
                    storeOP(resultView)
                }
            }
        }
    }

    fun onBackSpace(view: View) {

    }

    fun onAllClear(view: View) {

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