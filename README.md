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

Technologies Included:
* gesture listeners
* animations
* alternate resources
* Firebase user authentication
* Data stored and retrieved from Firebase
* RecyclerViews for displaying information
* Error and status dialogs
* SearchView widget
* Queries data from the Yelp API
* implicit intents
* Butterknife for binding views

## Product Backlog

__Priority PBI:__
* add undo or confirmation for delete (not started)

Secondary PBIs:
* implement a date picker dialog and time picker dialog for add incident form (not started)
* sort incidents by date (not started)
* put UI in fragment in MainActivity, and LogfileActivity (not started)
* Google map fragment in FindLegalListActivity to display markers for each of the offices near the current location (not started)
* implement an alternate resource for FindLegalListActivity when in landscape, with map and RecyclerView elements in a horizontal linear layout (not started)
* fix incident list item styling to mirror logfile list item styling (not started)

* material design to 'pretty up' the app (in progress)
  * Watch udacity course https://www.udacity.com/course/material-design-for-android-developers--ud862

Maybes:
* allow users to double tap on a log file to display the most recent incident entry (attempted with onTouchListener in ViewHolder -- needs different implementation)

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
