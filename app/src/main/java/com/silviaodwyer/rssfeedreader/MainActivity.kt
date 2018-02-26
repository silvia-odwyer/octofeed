package com.silviaodwyer.rssfeedreader

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "oncCreate called")
        val downloadData = DownloadData()
        downloadData.execute("URL goes here")
        Log.d(TAG, "onCreate: done")
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "Download Data"

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute: parameter is $result")
            }

            override fun doInBackground(vararg URL: String?): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d(TAG, "doInBackground: starts with ${URL[0]}")
                // If more than one URL is passed into the method, then
                // those extra URLS can be accessed via URL[1], URL[2], etc.,
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    // Log.e refers to error messages
                    Log.e(TAG, "doInBackground: Error downloading. :(")
                }
                return rssFeed
            }
        }

        }

        private fun downloadXML(urlPath: String?): String {
            val xmlResult = StringBuilder()

            try{
                val url = URL(urlPath)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val response = connection.responseCode
                Log.d(TAG, "downloadXML: The response code was $response")

//                val inputStream = connection.inputStream
//                val inputStreamReader = InputStreamReader(inputStream)
//                val reader = BufferedReader(inputStreamReader)
                val reader = BufferedReader(InputStreamReader(connection.inputStream))

                val inputBuffer = CharArray(500)
                var charsRead = 0
                while(charsRead >= 0){
                    charsRead = reader.read(inputBuffer)
                    if (charsRead > 0){
                        xmlResult.append(String(inputBuffer, 0, charsRead))
                    }
                }
            } catch(e: MalformedURLException){
                Log.e(TAG, "downloadXML: Invalid URL ${e.message}")
            } catch (e: IOException){
                Log.e(TAG, "downloadXML: IOException reading data: ${e.message}")
            }

            catch(e: Exception){
                Log.e(TAG, "Unknown error: ${e.message}")
            }


    }

}
