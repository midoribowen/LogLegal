# Log Legal

#### A legal logbook for users to track incidents, Monday April 11th, 2016

#### By Midori Bowen

## Description

Log Legal is a logbook where users can enter and track incidents. Each incident has a date, a time, witnesses, description, and a police badge number (optional).

A User can:
* register with an email and password, log in, and log out.
* manage their logbook:
  * view all the log files in their logbook
  * add a new log file, with a name
  * add an incident to a log file, which are sorted by the time they were added to the log file.


* search for law offices by zipcode and view them on a map with a list of law offices below
* view details about each law office by 'clicking' on a list item
* in the the detail view, click on the Yelp link and go to the link in a browser or in the Yelp app; click on phone numbers and dial the phone number on their app; click on an address and look up the address in the user's maps app


---------------

LogLegal is an independent project for the March 2016 Android class at Epicodus. It is a project that builds each week and therefore demonstrates proficiency in each week's lessons.

Code reviews currently included:
* __Gestures, Animations, & Flexible UIs (Week 4)__
  * Includes at least one gesture listener
  * Uses animations
  * Implements at least one alternate resource


* __Data Persistence (Week 3)__
  * Implement Firebase user authentication
  * Save and retrieve data using Firebase, with data structured properly (i.e. flat not nested)
  * Employ the Firebase-RecyclerAdapter to display from the database
  * Use SharedPreferences to save an important piece of data
  * Utilize dialogs to inform users of errors, login status, etc.
  * Use a SearchView widget to gather user input


* __Web Service Backends and Fragments (Week 2)__
  * Implement OkHttp to retrieve data from a backend
  * Create a data model to store the data
  * Display a list of information using a RecyclerView
  * Use fragments and a pager adapter to swipe through views
  * Incorporate at least one implicit intent


* __User Interface Basics (Week 1)__
  * At least 3 Activities the user can navigate to
  * Display a list of information
  * Gather user input and pass it to another activity
  * Use Butterknife to bind all views
  * Use View.OnClickListener interface to set click listeners


## To Do

__Priority:__
* put UI in fragment in MainActivity, and LogfileActivity
* implement an alternate resource for FindLegalListActivity when in landscape, with map and RecyclerView elements in a horizontal linear layout

Secondary:
* delete related incidents when logfiles are deleted
* add undo or confirmation for delete
* allow users to double tap on a log file to display the most recent incident entry
* implement a date picker dialog and time picker dialog for add incident form
* fix incident list item styling to mirror logfile list item styling

* Google map fragment in FindLegalListActivity to display markers for each of the offices near the current location
* Alternate landscape resource with map on left, RecyclerView on right
* material design to 'pretty up' the app
  * Watch udacity course https://www.udacity.com/course/material-design-for-android-developers--ud862

## Setup/Installation Requirements
You will need the following programs installed on your computer.
* Android Studio
* Java JDK 8+
* Android SDK

### To Run LogLegal
* In a terminal window, navigate to ~/AndroidStudioProjects
* Run `git clone https://github.com/midoribowen/LogLegal.git`
* Navigate to ~/AndroidStudioProjects/LogLegal
* Run on either an emulator or an Android OS Device connected to a computer

##### To set up an emulator
* Select Run > Run 'app'
* Click 'Create New Emulator'
* Select the device you would like to emulate (Recommended: Nexus 6)
* Select the API level you would like to run - click 'Download' if not available (Recommended: Marshmallow - ABI: x86)
* Select configuration settings for emulator
 * Recommended:
 * Scale: 4dp on device = 1px on screen
 * Camera - Front: Webcam()
 * Camera - Back: Webcam()
* Click 'Finish' and allow Emulator to run

##### To Run on an Android OS Device
* Connect the device to the computer through its USB port
* Make sure USB debugging is enabled (this may pop up in a window when you connect the device or it may need to be checked in the phone's settings)
* Select Run > Run 'app'
* Select the device (If it does not show, USB debugging is probably not enabled)
* Click 'OK'
* Wait... it takes a little while the first time

## Known Bugs

None currently!

## Support and contact details

Contact through GitHub username: midoribowen

## Technologies Used

AndroidStudio, Firebase, AndroidSDK, Java v1.8, Gradle

### License
Copyright (c) 2016 Midori Bowen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
