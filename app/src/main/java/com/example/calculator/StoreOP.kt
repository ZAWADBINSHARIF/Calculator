package com.example.calculator

import android.content.Context
import android.widget.TextView

fun storeOP(showResult : TextView) {
    val mainClass = MainActivity()
    val sOp = mainClass.sOp
    val num1 = mainClass.num1
    val num2 = mainClass.num2
    var sResutlt = mainClass.sResult

    when(sOp) {
        "+" -> {
            sResutlt = num1.plus(num2)
            showResult.text = "$sResutlt"
        }
        "-" -> {
            sResutlt = num1.minus(num2)
            showResult.text = "$sResutlt"
        }
        "*" -> {
            sResutlt = num1.times(num2)
            showResult.text = "$sResutlt"
        }
        "/" -> {
            sResutlt = num1.div(num2)
            showResult.text = "$sResutlt"
        }


    }
}