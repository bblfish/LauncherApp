# Launcher App

This is project to test the viability of [The Launcher App Proposal](https://github.com/solid/authorization-panel/blob/master/proposals/LauncherApp.md). It checks that one can use [Window.postMessage](https://developer.mozilla.org/en-US/docs/Web/API/Window/postMessage) to send messages between pages of different origins.

It does not implement the features that Window.postMessage would need to be really secure. At present I just wanted to test that a window from two origins can communicate.

## Conclusion

Window.postMessage does indeed allow messages to be sent between apps from different origins, if those are opened by the Launcher App. Furthermore this works when the Launcher App opens those apps in
* an IFrame
* a new tab
* a new browser window

## Todo
 
Immediate todos: 
 
* Test on a number of browsers and OSes.
* Improve the security of communication
* Add code for Launcher to send messages back to launched apps, and check those works.

Longer term todos:

* Test how much the Launcher App can control HTTP connections

## Setup

To get this working one needs to start a web server Apache on localhost to respond to requests from different origins.

In `/etc/hosts` add 

```/etc/hosts
127.0.0.1       alice.example bob.example app.example
```          

Then set up Apache to follow symlinks and to respond to the different origins. (See [the howto for MacOS](https://discussions.apple.com/docs/DOC-3083)).

```Apache
<Directory "/Users/USER/Sites/">
AllowOverride AuthConfig Limit
Options Indexes MultiViews FollowSymLinks
Order allow,deny
Allow from localhost
Allow from alice.example
Allow from bob.example
Allow from app.example
Satisfy Any
</Directory>
```                                   

From that directory create a symlink to the cloned github download, and make sure all subdirectories are world readable so that Apache can see them.

Compile the content using 
```scala 
$ sbt
> fastOptJS
```             

In a different shell make sure the JS artefacts created by ScalaJS are world readable. See StackOverflow question [How do I specify permissions for sbt artefacts](https://stackoverflow.com/questions/58519368/how-do-i-specify-permissions-for-sbt-artefacts)

Open up the `html/Launcher.html` as `http://alice.example/~USER/LauncherApp/html/Laucher.html` file in the browser. 

In the url field you can then easily adjust the remote app url, and reload the iframe or open a new tab.

Clicking the button in the iframe which is loaded from `app.example` should send a message to the main window. 

To open the remote App in a new frame click the text "Open Remote app in new Frame.". That should open a new tab, and clicking the button there will also send a message to the frame that launched it.
