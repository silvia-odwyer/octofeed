package com.silviaodwyer.rssfeedreader

import org.xmlpull.v1.XmlPullParser

/**
 * Created by silvi on 01/03/2018.
 */
class ParseApplications {
    private val TAG = "ParseApplications"
    val applications = ArrayList<FeedEntry>()

    fun parse(xmlData: String): Boolean{
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue = ""

        try{
            val factory = XsmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())  // String readers create streams which can read strings
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT){

            }
        }
        catch (e: Exception){
            e.printStackTrace()
            status = false

        }
        return false































    }
}