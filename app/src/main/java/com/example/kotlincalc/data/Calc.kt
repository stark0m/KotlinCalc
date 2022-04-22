package com.example.kotlincalc.data

import com.example.kotlincalc.enums.Buttons

class Calc {
    var cuttentString: String = "0"
        private set
    private var arg2: Double = 0.0
    private var arg1: Double = 0.0
    private var activeAction: Buttons = Buttons.NOTHING
    private var isNeedToCleanArgs: Boolean = false


    fun getArg2() = arg2
    fun getAction(): String = when (activeAction) {
        Buttons.DIV -> "/"
        Buttons.MULT -> "*"
        Buttons.ADD -> "+"
        Buttons.MINUS -> "-"
        else -> ""
    }

    fun isClicketResultAndBufferClear(action: Buttons) =
        (activeAction == Buttons.NOTHING && action != Buttons.RESULT)


    fun doResult() {
        if (activeAction == Buttons.NOTHING) {
            return
        }
        if (activeAction == Buttons.DIV && arg1 == 0.0) {
            return
        }

        arg1 = when (activeAction) {
            Buttons.ADD -> arg2 + arg1
            Buttons.MINUS -> arg2 - arg1
            Buttons.MULT -> arg2 * arg1
            Buttons.DIV -> arg2 / arg1
            else -> return
        }
        activeAction = Buttons.NOTHING
        cuttentString = arg1.toString()
        arg2 = 0.0
        isNeedToCleanArgs = true

    }

    fun doAction(actionClicked: Buttons) {
        // деление на 0 проверяем
        if (activeAction == Buttons.DIV && arg1 == 0.0) {
            return
        }


        if (arg1 != 0.0) {
            arg2 = arg1
        }




        activeAction = actionClicked
        arg1 = 0.0
        cuttentString = "0"
    }


    fun getArg1() = arg1
    fun numClicked(button: Buttons) {
        if (isNeedToCleanArgs) {
            this.cuttentString = ""
        }
        isNeedToCleanArgs = false
        cuttentString += when (button) {
            Buttons.ONE -> "1"
            Buttons.TWO -> "2"
            Buttons.THREE -> "3"
            Buttons.FOUR -> "4"
            Buttons.FIVE -> "5"
            Buttons.SIX -> "6"
            Buttons.SEVEN -> "7"
            Buttons.EIGHT -> "8"
            Buttons.NINE -> "9"
            Buttons.ZERO -> "0"
            else -> ""
        }
        arg1 = cuttentString.toDouble()

    }

    fun dotClicked() {
        if (cuttentString.indexOf('.') != -1) {
            return
        } else cuttentString += "."

    }


}