package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var tvInput: TextView? = null
    private var btnZero: Button? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnDivide: Button? = null
    private var btnMul: Button? = null
    private var btnAdd: Button? = null
    private var btnSub: Button? = null
    private var btnClr: Button? = null
    private var btnDot: Button? = null
    private var btnEqual: Button? = null
    var lastNumeric: Boolean = true
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnDivide = findViewById(R.id.btnDivide)
        btnClr = findViewById(R.id.btnClr)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSubtract)
        btnMul = findViewById(R.id.btnMultiply)
        btnEqual = findViewById(R.id.btnEqual)
        btnDot = findViewById(R.id.btnDot)

        btnZero?.setOnClickListener(this)
        btnOne?.setOnClickListener(this)
        btnTwo?.setOnClickListener(this)
        btnThree?.setOnClickListener(this)
        btnFour?.setOnClickListener(this)
        btnFive?.setOnClickListener(this)
        btnSix?.setOnClickListener(this)
        btnSeven?.setOnClickListener(this)
        btnEight?.setOnClickListener(this)
        btnNine?.setOnClickListener(this)
        btnMul?.setOnClickListener(this)
        btnSub?.setOnClickListener(this)
        btnAdd?.setOnClickListener(this)
        btnDivide?.setOnClickListener(this)
        btnClr?.setOnClickListener(this)
        btnEqual?.setOnClickListener(this)
        btnDot?.setOnClickListener(this)
    }

    private fun onDigit(view: View?) {
        tvInput?.append((view as? Button)?.text)
        lastNumeric = true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnZero -> onDigit(p0)
            R.id.btnOne -> onDigit(p0)
            R.id.btnTwo -> onDigit(p0)
            R.id.btnThree -> onDigit(p0)
            R.id.btnFour -> onDigit(p0)
            R.id.btnFive -> onDigit(p0)
            R.id.btnSix -> onDigit(p0)
            R.id.btnSeven -> onDigit(p0)
            R.id.btnEight -> onDigit(p0)
            R.id.btnNine -> onDigit(p0)
            R.id.btnClr -> tvInput?.text = ""
            R.id.btnDot -> {
                if (lastNumeric && !lastDot) {
                    tvInput?.append((p0 as? Button)?.text)
                    lastNumeric = false //flags
                    lastDot = true
                }
            }
            R.id.btnAdd -> onOperator(p0)
            R.id.btnSubtract -> onOperator(p0)
            R.id.btnMultiply -> onOperator(p0)
            R.id.btnDivide -> onOperator(p0)
            R.id.btnEqual -> onEqual(p0)
        }
    }

    private fun onOperator(view: View?) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as? Button)?.text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    private fun onEqual(view: View?) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)// -99 -> 99
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (value.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}