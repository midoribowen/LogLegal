# Log Legal

#### A legal logbook for users to track incidents, Sunday March 27th, 2016

#### By Midori Bowen

## Description

Log Legal is a logbook where users can enter and track incidents. Each incident has a date, a time, witnesses, description, and a police badge number (optional). This application is an independent project for the March 2016 Android class at Epicodus. It is a project that builds each week and therefore demonstrates the week's lessons at the end of each week.
A User can:
* add an incident to their logbook, which they can view and sort
* change their settings (username, password)
* view law offices and advocacy organizations near them on a map with a list of places below
* click on the Yelp link and go to the link in a browser or in the Yelp app; click on phone numbers and dial the phone number on their app; click on an address and look up the address in the user's Google Maps app

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

* If you select Log In in the overflow menu and go to LoginActivity, this does not do anything. Real Log in with authentication is not enabled yet. This is just a preview of this feature.

###### HAXM Errors

If you receive an error about HAX or HAXM, you may not have HAXM installed or HAXM may be installed improperly.

Open Android Studio > Preferences and navigate to the SDK Manager through the Preferences menu (Appearance & Behavior > System Settings > Android SDK).

 Then click the link for 'Launch Standalone SDK Manager'. Scroll to the last item in this list to 'Intel X86 Emulator Accelerator (HAXM installer)'. If this says 'Installed', the problem is probably this: [How to Fix: "HAX is not working and emulator runs in emulation mode"](http://stackoverflow.com/questions/21031903/how-to-fix-hax-is-not-working-and-emulator-runs-in-emulation-mode).

 Select the checkbox next to this package and click 'Delete 1 package'. After it is deleted, try to reinstall with 1536MB of RAM allowed by HAXM, by following the instructions in the link below.

 If it is not installed, follow the instructions here: [Installation Instructions for Intel® Hardware Accelerated Execution Manager - Mac OS X*](https://software.intel.com/en-us/android/articles/installation-instructions-for-intel-hardware-accelerated-execution-manager-mac-os-x). Make sure to allow at least 1536MB of RAM.

## Support and contact details

Contact through GitHub username: midoribowen

## Technologies Used

AndroidStudio, AndroidSDK, Java v1.8, Gradle

### License
Copyright (c) 2016 Midori Bowen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
