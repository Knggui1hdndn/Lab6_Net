package com.example.lab6

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtFC: EditText
    private lateinit var btnF: Button
    private lateinit var btnC: Button
    private lateinit var tvResult: TextView
    private var convertStyle: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtFC = findViewById(R.id.edtF_C)
        btnF = findViewById(R.id.btnF)
        btnC = findViewById(R.id.btnC)
        tvResult = findViewById(R.id.tvResult)

        btnF.setOnClickListener(this)
        btnC.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnF -> {
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION, Constants.F_TO_C_METHOD_NAME)
                convertStyle = 1
            }
            R.id.btnC -> {
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION, Constants.C_TO_F_METHOD_NAME)
                convertStyle = 0
            }
        }
    }

    // create and execute asynctask to get response from W3school server
    private fun invokeAsyncTask(convertParams: String, soapAction: String, methodName: String) {
        ConvertTemperatureTask(this, soapAction, methodName, convertParams).execute(edtFC.text.toString().trim())
    }

    // call back data from background thread and set to views
    fun callBackDataFromAsyncTask(result: String) {
        val resultTemperature = result.toDoubleOrNull() ?: return

        val temperatureText = edtFC.text.toString().trim()
        val resultText = String.format("%.2f", resultTemperature)

        val resultMessage = if (convertStyle == 0) {
            "$temperatureText degree Celsius = $resultText degree Fahrenheit"
        } else {
            "$temperatureText degree Fahrenheit = $resultText degree Celsius"
        }

        tvResult.text = resultMessage
    }
}
