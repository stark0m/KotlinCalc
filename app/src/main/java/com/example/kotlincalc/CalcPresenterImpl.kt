package com.example.kotlincalc

import com.example.kotlincalc.data.Calc
import com.example.kotlincalc.enums.Buttons
import com.example.kotlincalc.interfaces.CalcPresenter
import com.example.kotlincalc.interfaces.ViewInterface

class CalcPresenterImpl(val view:ViewInterface):CalcPresenter {

    var calculator=Calc()

    override fun switched(value: Buttons) {
        when(value){
            Buttons.ONE-> calculator.numClicked(value)
            Buttons.TWO-> calculator.numClicked(value)
            Buttons.THREE-> calculator.numClicked(value)
            Buttons.FOUR-> calculator.numClicked(value)
            Buttons.FIVE-> calculator.numClicked(value)
            Buttons.SIX-> calculator.numClicked(value)
            Buttons.SEVEN-> calculator.numClicked(value)
            Buttons.EIGHT-> calculator.numClicked(value)
            Buttons.NINE-> calculator.numClicked(value)
            Buttons.ZERO-> calculator.numClicked(value)
            Buttons.ADD-> calculator.doAction(value)
            Buttons.MINUS-> calculator.doAction(value)
            Buttons.MULT-> calculator.doAction(value)
            Buttons.DIV-> calculator.doAction(value)
            Buttons.DOT-> calculator.dotClicked()
            Buttons.RESULT->calculator.doResult()
            Buttons.CLEAR->calculator = Calc()
        }


        showInView()

    }

    override fun showInView() {
        view.drawCalc()
    }


}