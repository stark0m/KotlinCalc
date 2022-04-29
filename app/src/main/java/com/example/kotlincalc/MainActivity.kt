package com.example.kotlincalc

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kotlincalc.data.Calc
import com.example.kotlincalc.enums.Buttons
import com.example.kotlincalc.interfaces.ViewInterface
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import java.text.DecimalFormat

const val CALC = "CALC"
const val THEME_PREFS = "THEME_PREFS_FILE"
const val CURRENT_THEME = "CURRENT_THEME"

class MainActivity : AppCompatActivity(), ViewInterface {

    lateinit var button_0: Button
    lateinit var button_1: Button
    lateinit var button_2: Button
    lateinit var button_3: Button
    lateinit var button_4: Button
    lateinit var button_5: Button
    lateinit var button_6: Button
    lateinit var button_7: Button
    lateinit var button_8: Button
    lateinit var button_9: Button
    lateinit var button_mult: Button
    lateinit var button_div: Button
    lateinit var button_add: Button
    lateinit var button_minus: Button
    lateinit var button_result: Button
    lateinit var button_dot: Button
    lateinit var button_clear: Button

    lateinit var text_active: TextView
    lateinit var text_intermediate: TextView
    lateinit var text_action: TextView

    lateinit var topAppBar: MaterialToolbar
    lateinit var sharedPreferences:SharedPreferences


    private var nf = DecimalFormat("#.#####");

    val presenter = CalcPresenterImpl(this)

    var currentTheme:Int
        set(value) {
            sharedPreferences.edit().putInt(CURRENT_THEME, value).apply()
        }
        get() = sharedPreferences.getInt(CURRENT_THEME, R.style.Theme_KotlinCalc_dark)



    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(CALC, presenter.calculator)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.getSharedPreferences(THEME_PREFS, MODE_PRIVATE)
        setTheme(currentTheme)
        setContentView(R.layout.activity_main)

        button_0 = findViewById(R.id.key_zero)
        button_1 = findViewById(R.id.key_one)
        button_2 = findViewById(R.id.key_two)
        button_3 = findViewById(R.id.key_three)
        button_4 = findViewById(R.id.key_four)
        button_5 = findViewById(R.id.key_five)
        button_6 = findViewById(R.id.key_six)
        button_7 = findViewById(R.id.key_seven)
        button_8 = findViewById(R.id.key_eight)
        button_9 = findViewById(R.id.key_nine)
        button_mult = findViewById(R.id.key_multiply)
        button_div = findViewById(R.id.key_divide)
        button_add = findViewById(R.id.key_plus)
        button_minus = findViewById(R.id.key_minus)
        button_result = findViewById(R.id.key_return)
        button_dot = findViewById(R.id.key_dot)
        text_active = findViewById(R.id.calc_result)
        text_intermediate = findViewById(R.id.intermediate_value_number)
        text_action = findViewById(R.id.intermediate_action_value)
        button_clear = findViewById(R.id.key_clear)
        topAppBar = findViewById(R.id.topAppBar)

        button_0.setOnClickListener({ presenter.switched(Buttons.ZERO) })
        button_1.setOnClickListener({ presenter.switched(Buttons.ONE) })
        button_2.setOnClickListener({ presenter.switched(Buttons.TWO) })
        button_3.setOnClickListener({ presenter.switched(Buttons.THREE) })
        button_4.setOnClickListener({ presenter.switched(Buttons.FOUR) })
        button_5.setOnClickListener({ presenter.switched(Buttons.FIVE) })
        button_6.setOnClickListener({ presenter.switched(Buttons.SIX) })
        button_7.setOnClickListener({ presenter.switched(Buttons.SEVEN) })
        button_8.setOnClickListener({ presenter.switched(Buttons.EIGHT) })
        button_9.setOnClickListener({ presenter.switched(Buttons.NINE) })
        button_mult.setOnClickListener({ presenter.switched(Buttons.MULT) })
        button_div.setOnClickListener({ presenter.switched(Buttons.DIV) })
        button_add.setOnClickListener({ presenter.switched(Buttons.ADD) })
        button_minus.setOnClickListener({ presenter.switched(Buttons.MINUS) })
        button_result.setOnClickListener({ presenter.switched(Buttons.RESULT) })
        button_dot.setOnClickListener({ presenter.switched(Buttons.DOT) })
        button_clear.setOnClickListener({ presenter.switched(Buttons.CLEAR) })




        initListeners();

        if (savedInstanceState != null) {
            savedInstanceState.getParcelable<Calc>(CALC)
                ?.let { presenter.calculator.restoreCalc(it) }
        }
        presenter.showInView()

    }

    private fun initListeners() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dark_theme -> {
                    currentTheme = R.style.Theme_KotlinCalc_dark
                    recreate()
                    true
                }
                R.id.light_theme -> {
                    currentTheme = R.style.Theme_KotlinCalc
                    recreate()
                    true
                }

                else -> false
            }
        }
    }

    override fun drawCalc() {

        text_active?.text = nf.format(presenter.calculator.getArg1()).toString()

        text_action?.text = presenter.calculator.getAction()
        text_intermediate.text = nf.format(presenter.calculator.getArg2()).toString()


    }


}