package com.example.tipcalculator

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var evAmount: EditText
    private lateinit var SBPercent: SeekBar
    private lateinit var tvTipPercent: TextView
    private lateinit var tvTip: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        evAmount = findViewById(R.id.evAmount)
        SBPercent = findViewById(R.id.SBPercent)
        tvTipPercent = findViewById(R.id.tvTipPercent)
        tvTip = findViewById(R.id.tvTip)
        tvTotal = findViewById(R.id.tvTotal)

        addEditTextListener()
        addSeekbarListener()
    }

    private fun addEditTextListener() {

        evAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("MainActivity", s?.toString() ?: "")
                computeTip()
            }
        })
    }

    private fun addSeekbarListener(){
        SBPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(s: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("MainActivity", "$progress%")
                tvTipPercent.text = "$progress%"

                computeTip()
            }

            override fun onStartTrackingTouch(s: SeekBar?) {
            }

            override fun onStopTrackingTouch(s: SeekBar?) {
            }

        })
    }

    private fun computeTip(){
        val amount = evAmount.text.toString().toDoubleOrNull() ?: 0.0
        val percentage: Int = SBPercent.progress

        val tip = amount * (percentage/100.0)
        val total = amount+tip
        tvTip.text = "%.2f".format(tip)
        tvTotal.text = "%.2f".format(total)
    }
}

