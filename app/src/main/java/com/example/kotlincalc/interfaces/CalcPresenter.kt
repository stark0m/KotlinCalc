package com.example.kotlincalc.interfaces

import com.example.kotlincalc.enums.Buttons

interface CalcPresenter {
    fun switched(value:Buttons)
    fun showInView()
}