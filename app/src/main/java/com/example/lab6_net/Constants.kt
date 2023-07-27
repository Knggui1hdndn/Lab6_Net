package com.example.lab6

object Constants {
    const val SOAP_ACTION = "https://www.w3schools.com/xml/"
    const val NAME_SPACE = "https://www.w3schools.com/xml/"
    const val URL = "https://www.w3schools.com/xml/tempconvert.asmx?WSDL"
    const val F_TO_C_METHOD_NAME = "FahrenheitToCelsius"
    const val C_TO_F_METHOD_NAME = "CelsiusToFahrenheit"
    const val F_TO_C_SOAP_ACTION = SOAP_ACTION +
            F_TO_C_METHOD_NAME
    const val C_TO_F_SOAP_ACTION = SOAP_ACTION +
            C_TO_F_METHOD_NAME
}