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

    }

    fun onOperator(view: View) {

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