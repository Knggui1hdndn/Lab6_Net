package com.example.lab6

import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE


object WebServiceCall {
    private val TAG = WebServiceCall::class.java.simpleName
    fun callWSThreadSoapPrimitive(
        strURL: String?,
        strSoapAction: String?,
        request: SoapObject?
    ): String? {
        return try {
            val result: StringBuffer
            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            envelope.dotNet = true
            envelope.setOutputSoapObject(request)
            val ht = HttpTransportSE(strURL)
            ht.debug = true
            ht.call(strSoapAction, envelope)
            val response = envelope.response as SoapPrimitive
            result = StringBuffer(response.toString())
            Log.i(TAG, "result: $result")
            result.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}