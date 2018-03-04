package com.silviaodwyer.rssfeedreader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

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
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())  // String readers create streams which can read strings
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name?.toLowerCase()
                when (eventType){
                    // When the parser reaches a Start Tag, it checks if the tag Name is 'entry'
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
                                // if the tag is entry, this means that it has reached the end of the entry info. and can add all the details to currentRecord.
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry() // create a new object
                                }
                                "title" -> currentRecord.title = textValue
                                "published" -> currentRecord.published = textValue
//                                "name" -> currentRecord.name = textValue
//                                "artist" -> currentRecord.artist = textValue
//                                "releasedate" -> currentRecord.releaseDate = textValue
//                                "summary" -> currentRecord.summary = textValue
//                                "image" -> currentRecord.imageURL = textValue
                            }
                        }
                    }
                }
                eventType = xpp.next()


            }

            for (app in applications){
                Log.d(TAG, "*************")
                Log.d(TAG, app.toString())
            }

        }
        catch (e: Exception){
            e.printStackTrace()
            status = false

        }
        return false



    }
}