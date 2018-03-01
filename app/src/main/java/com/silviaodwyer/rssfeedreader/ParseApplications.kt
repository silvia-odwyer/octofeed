package com.silviaodwyer.rssfeedreader

import android.util.Log
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
                val tagName = xpp.name.toLowerCase()
                when (eventType){
                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "parse: Starting tag for " + tagName)
                        if (tagName == "entry"){
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG -> {
                        Log.d(TAG, "Parse: Ending tag for " + tagName)
                        if(inEntry){
                            when(tagName){
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry() // create a new object
                                }
                                name -> currentRecord.name = textValue
                                "artist" -> currentRecord.artist = textValue
                                "releaseDate" - > currentRecord.releaseDate = textValue
                                        "summary" -> currentRecord.summary = textValue
                                "image" - > currentRecord.imageURL = textValue
                            }
                        }
                    }
                }


            }

            eventType = xpp.next()
        }
        catch (e: Exception){
            e.printStackTrace()
            status = false

        }
        return false































    }
}