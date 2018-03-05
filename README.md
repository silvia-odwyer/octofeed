# KotlinRSSFeedReader
A Kotlin Android app that can parse and display a GitHub user's Atom feed

## Screenshots
Screenshots can explain more than any amount of words :)
Here's the app with Material Design UI:
![The app with Material Design UI](https://github.com/silvia-odwyer/KotlinRSSFeedReader/blob/master/App_With_Material_UI_Screenshot.PNG)

Yeah, the UI needs a lot of work, but I might make it dark-themed in the coming week or so, preferably
to make it look like Git Bash or a command-line of some sort.

## How To Use
All a user needs to do is enter their GitHub username upon app launch. 

Then upon pressing the 'Get Feed' button, the app will insert the username into the URL
and scrape the user's Atom feed containing their most recent GitHub activity. 

The feed will then be parsed, analysed, and the relevant tags pulled out of the feed. 
This occurs in the background thread, using Async Task. 

Then, the custom adapter will help communication between the ListView in the activitiy_main.xml
and the XML parser, so that the correct text can be inserted into each item in the ListView.

## Not Working?
Make sure you do the following:
- After typing in your GitHub username, press the 'Get Feed' button *only once* and once only. 
The background thread will re-run or create a new thread if you press it twice, resulting in the app crashing.
- Wait a few seconds for the XML scraper to download all the XML and parse it accordingly. 
If you find yourself running into any bugs, just leave a comment or open a pull request.
- By the way, check the "Current Bugs That Need To Be Fixed" section below.

## Future Development Plans
- Darkify the UI, make it look like a terminal or CLI.
- Create a Splash Screen/Loading Screen (cos they're pretty cool and professional-looking)

## Current Bugs That Need To Be Fixed
- You can only enter one GitHub username per app launch, because the app crashes if you try
enter another. This is largely due to the Async Task not stopping once the feed has been downloaded.
NOTE: If you find yourself wanting to get another user's GitHub feed, you'll need to close the app and 
re-open it again. Sorry about any inconvenience caused by the way, I'm on my way to fixing it! :)
