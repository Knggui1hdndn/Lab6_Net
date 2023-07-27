package com.example.lab6

import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import org.ksoap2.serialization.SoapObject

class ConvertTemperatureTask(
    private val activity: MainActivity,
    private val soapAction: String,
    private val methodName: String,
    private val paramsName: String
) : AsyncTask<String, Void, String>() {

    private var pDialog: ProgressDialog? = null

    override fun onPreExecute() {
        super.onPreExecute()
        pDialog = ProgressDialog(activity)
        pDialog?.setMessage("Please wait...")
        pDialog?.setCancelable(false)
        pDialog?.show()
    }

    override fun doInBackground(vararg params: String): String? {
        // create a new soap request object
        val request = SoapObject(
            Constants.NAME_SPACE,
            methodName
        )
        // add properties for soap object
        request.addProperty(paramsName, params[0])
        // request to server and get Soap Primitive response
        return WebServiceCall.callWSThreadSoapPrimitive(
            Constants.URL,
            soapAction, request
        )
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        pDialog?.dismiss()
        if (result == null) {
            Log.i("check", "cannot get result")
        } else {
            Log.i("check", result)
            // invoke call back method of Activity
            activity.callBackDataFromAsyncTask(result)
        }
    }
}
