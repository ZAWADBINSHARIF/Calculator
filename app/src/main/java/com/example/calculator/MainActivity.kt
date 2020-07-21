package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.Math.pow
import kotlin.math.*

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
    private var canPercentage = false
    private var oneResult = false
    private var oneEquationResult: Double? = null
    private var canFactorial = true
    private var canPower = false
    private var canTenPow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        calculationView.movementMethod = ScrollingMovementMethod()
        firstMinus = false
    }

    fun onDigit(view: View) {

        if(resultView.text.length > 10)
            resultView.textSize = 25f
        else
            resultView.textSize = 55f

        if (
            !(resultView.text.contains("NaN")) &&
            !(resultView.text.contains("Infinity") &&
                    !resultView.text.contains('π'))
        ) {

        if (showResultCalculation == true){
            resultView.text = ""
            showResultCalculation = false
        }

        resultView.append((view as Button).text)
        showOP = false
        firstMinus = true
        canPercentage = true
        }

    }

    fun onOperator(view: View) {

        val op = (view as Button).text.toString()

        if (        !(resultView.text.endsWith("y") ||
                    resultView.text.startsWith("I") ||
                    resultView.text.endsWith("N") ||
                    resultView.text.startsWith("N") ||
                    resultView.text.isEmpty())) {
            if (showEqual) {

                showOP = false
                firstMinus = false
                showResult = null
                storeOP = null
                showResultCalculation = false
                showEqual = false
                canPercentage = false
                oneResult = false
                oneEquationResult = null
                canPower = false
                canTenPow = false

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
        calculationView.text = ""
        resultView.text = ""
        newCalculationNum = 1
        showOP = true
        firstMinus = false
        num1 = null
        num2 = 0.0
        sResult = null
        showResult = null
        storeOP  = null
        showResultCalculation = null
        showEqual = false
        canPercentage = false
        canPower = false
        canTenPow = false
        oneResult = false
        oneEquationResult = null
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

    fun onPercentage(view: View) {
        val showFactorial = resultView.text.contains('!')
        val showPercentage = resultView.text.contains('%')
        val showSin = resultView.text.contains("SIN")
        val showCos = resultView.text.contains("COS")
        val showTan = resultView.text.contains("TAN")
        val showLog = resultView.text.contains("LOG")
        val showPow = resultView.text.contains('^')
        val showRoot = resultView.text.contains('√')

        if (!showPercentage && canPercentage &&
                !(showPercentage && showCos && showSin && showTan && showLog && showPow && showRoot)) {
            resultView.append((view as Button).text)
            canPercentage = true
        }
    }

    fun onFactorial(view: View) {
        val showPercentage = resultView.text.contains('%')
        val showFactorial = resultView.text.contains('!')
        val showSin = resultView.text.contains("SIN")
        val showCos = resultView.text.contains("COS")
        val showTan = resultView.text.contains("TAN")
        val showLog = resultView.text.contains("LOG")
        val showPow = resultView.text.contains('^')
        val showRoot = resultView.text.contains('√')

        if (!showFactorial &&
            canFactorial &&
            resultView.text.isNotEmpty() &&
                !(showPercentage && showCos && showSin && showTan && showLog && showPow && showRoot)) {
            resultView.append((view as Button).text)
            canFactorial = true
        }
    }

    private fun factorial(num: Double): Double {
        var factorial : Double = 1.0

        for (i in 1..num.toInt())
            factorial *= i

        this.oneEquationResult = factorial
        return oneEquationResult as Double
    }

    fun onINV(view: View) {
        val showFactorial = resultView.text.contains('!')
        val showPercentage = resultView.text.contains('%')
        val showSin = resultView.text.contains("SIN")
        val showCos = resultView.text.contains("COS")
        val showTan = resultView.text.contains("TAN")
        val showLog = resultView.text.contains("LOG")
        val showPow = resultView.text.contains('^')
        val showRoot = resultView.text.contains('√')

        if (resultView.text.isNotEmpty() &&
            !(resultView.text.endsWith("y") ||
                    resultView.text.startsWith("I") ||
                    resultView.text.endsWith("N") ||
                    resultView.text.startsWith("N")) &&
            (!showFactorial &&
                    canFactorial &&
                    resultView.text.isNotEmpty() &&
                    !(showPercentage && showCos && showSin && showTan && showLog && showPow && showRoot && showPercentage)))
        {
            val invNum = "${resultView.text.toString().toDouble() * -1}"

            val removeDot = invNum.endsWith(".0")
            val newShowResult = invNum.substring(0, invNum.toString().length - 2)

            if (removeDot) {
                resultView.text = newShowResult
            }else {
                resultView.text = invNum
            }
        }
    }

    fun onSin(v : View) {

        if(resultView.text.isEmpty())
        {
            resultView.append((v as Button).text)
        }
    }

    fun onCos(v : View) {

        if(resultView.text.isEmpty())
        {
            resultView.append((v as Button).text)
        }
    }

    fun onTan(v : View) {

        if(resultView.text.isEmpty())
        {
            resultView.append((v as Button).text)
        }
    }

    fun onLog(v : View) {
        if(resultView.text.isEmpty())
        {
            resultView.append((v as Button).text)
        }
    }

    fun onRoot(v : View) {

        if(resultView.text.isEmpty())
        {
            resultView.append((v as Button).text)
        }
    }

    fun onPI(view: View) {
        if(resultView.text.isEmpty())
            resultView.append((view as Button).text)
        if(showResultCalculation == true)
            resultView.text = ""
    }

    fun onPower (view: View) {
        if(resultView.text.isNotEmpty() && !canPower && !canTenPow) {
            resultView.append((view as Button).text)
            canPower = true
        }
    }

    fun onTenPow (view: View) {
        if(resultView.text.isNotEmpty() && !canPower && !canTenPow) {
            resultView.append((view as Button).text)
            canTenPow = true
        }
    }

    private fun storeResult(sNumber: String) : Double {

        return when {

            resultView.text.endsWith('%') -> {
                val percentageNumber = sNumber.substring(0, sNumber.length - 1)
                sResult = (percentageNumber.toDouble()) / 100
                sResult as Double
            }

            resultView.text.endsWith('!') -> {
                val factorialNumber = sNumber.substring(0, sNumber.length - 1)
                 sResult = factorial(factorialNumber.toDouble())
                sResult as Double
            }

            resultView.text.startsWith("SIN") -> {
                val perfactNum = sNumber.substring(3, sNumber.length)
                sResult = sin(perfactNum.toDouble() * 2 * (PI/360))
                sResult as Double
            }

            resultView.text.startsWith("COS") -> {
                val perfactNum = sNumber.substring(3, sNumber.length)
                sResult = cos(perfactNum.toDouble() * 2 * (PI/360))
                sResult as Double
            }

            resultView.text.startsWith("TAN") -> {
                val perfactNum = sNumber.substring(3, sNumber.length)
                sResult = tan(perfactNum.toDouble()* 2 * (PI/360))
                sResult as Double
            }

            resultView.text.startsWith("LOG") -> {
                val perfactNum = sNumber.substring(3, sNumber.length)
                sResult = log10(perfactNum.toDouble())
                sResult as Double
            }

            resultView.text.startsWith("√") -> {
                val perfactNum = sNumber.substring(1, sNumber.length)
                sResult = sqrt(perfactNum.toDouble())
                sResult as Double
            }

            resultView.text.startsWith("π") -> {
                sResult = PI
                sResult as Double
            }

            resultView.text.contains("^") && !canTenPow && canPower -> {
                val power = resultView.text.split('^')
                val num = power[0]
                val powerNum = power[1]

                sResult = num.toDouble().pow(powerNum.toDouble())
                canPower = false
                sResult as Double
            }

            resultView.text.contains("*10^") && canTenPow && !canPower -> {
                val tenPow = resultView.text.split("*10^")
                val num = tenPow[0]
                val powerNum = tenPow[1]

                sResult = num.toDouble() * (10.toDouble().pow(powerNum.toDouble()))
                canTenPow = false
                sResult as Double
            }



            else -> {
                sResult = sNumber.toDouble()
                sResult as Double
            }
        }
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

        if (!resultView.text.startsWith("N") || !resultView.text.endsWith("N")) {

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

    private fun oneEquation(Values: String) : Double? {
        return when {
        resultView.text.endsWith('%') -> {
            val percentageNumber = Values.substring(0, Values.length - 1)
            oneEquationResult = (percentageNumber.toDouble()) / 100
            oneEquationResult as Double
        }

        resultView.text.endsWith('!') -> {
            val factorialNumber = Values.substring(0, Values.length - 1)
            val fact = factorialNumber.toDouble()
            factorial(fact)
        }

            resultView.text.startsWith("SIN") -> {
                val perfactNum = Values.substring(3, Values.length)
                oneEquationResult = sin(perfactNum.toDouble() * 2 * (PI/360))
                oneEquationResult as Double
            }

            resultView.text.startsWith("COS") -> {
                val perfactNum = Values.substring(3, Values.length)
                oneEquationResult = cos(perfactNum.toDouble() * 2 * (PI/360))
                oneEquationResult as Double
            }

            resultView.text.startsWith("TAN") -> {
                val perfactNum = Values.substring(3, Values.length)
                oneEquationResult = tan(perfactNum.toDouble()* 2 * (PI/360))
                oneEquationResult as Double
            }

            resultView.text.startsWith("LOG") -> {
                val perfactNum = Values.substring(3, Values.length)
                oneEquationResult = log10(perfactNum.toDouble())
                oneEquationResult as Double
            }

            resultView.text.startsWith("√") -> {
                val perfactNum = Values.substring(1, Values.length)
                oneEquationResult = sqrt(perfactNum.toDouble())
                oneEquationResult as Double
            }

            resultView.text.startsWith("π") -> {
                oneEquationResult = PI
                oneEquationResult as Double
            }

            resultView.text.contains("^") && !canTenPow && canPower -> {
                val power = resultView.text.split('^')
                val num = power[0]
                val powerNum = power[1]

                oneEquationResult = num.toDouble().pow(powerNum.toDouble())
                canPower = false
                oneEquationResult as Double
            }

            resultView.text.contains("*10^") && canTenPow && !canPower -> {
                val tenPow = resultView.text.split("*10^")
                val num = tenPow[0]
                val powerNum = tenPow[1]

                oneEquationResult = num.toDouble() * (10.toDouble().pow(powerNum.toDouble()))
                canTenPow = false
                oneEquationResult as Double
            }

            else -> {
                oneEquationResult = null
                return oneEquationResult as Double
            }
        }
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
                    resultView.text = newShowResult
                }else {
                    calculationView.append("__________________\n$showResult\n")
                    resultView.text = showResult.toString()
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

        if ((resultView.text.contains('%')) ||
            (resultView.text.contains('!')) ||
            resultView.text.contains("SIN")||
            resultView.text.contains("COS")||
            resultView.text.contains("TAN")||
            resultView.text.contains("LOG")||
            resultView.text.contains('^')||
            resultView.text.contains('√')||
                resultView.text.contains('π')||
                resultView.text.contains("*10^"))
        {
            oneEquation(resultView.text.toString())

            val removeDot = oneEquationResult!!.toString().endsWith(".0")
            val newOneEquationResult = oneEquationResult!!.toString().substring(0, oneEquationResult.toString().length - 2)

            if(removeDot) {
                resultView.text = newOneEquationResult
                calculationView.append("\n$newOneEquationResult\n\n")
            }else{
                resultView.text = "$oneEquationResult"
                calculationView.append("\n$oneEquationResult\n\n")
            }
            showEqual = true
            showResultCalculation = true
        }
    }
}
