monix-ionic-sample
==================

Sample of a mobile client app / web application demonstrating the streaming of values over web-socket, using Monix on both ends.

This project is based on [scalajs-ionic-starttabs](https://github.com/olivergg/scalajs-ionic-starttabs) by @olivergg for the mobile app
and on [monix-sample](https://github.com/monifu/monix-sample) by @alexandru for using Monix in Scala and ScalaJS.


It consists of the following sub-projects:
- **server** the server side application, built with Play 2.4
- **shared** code shared between the server and the mobile application, cross compiled for Scala and ScalaJS
- **app-jvm** the mobile client html code, compiled from Scala code using [scalatags](https://github.com/lihaoyi/scalatags)
- **app-js** the mobile application, built on [Ionic](http://ionicframework.com/) with ScalaJS

Overview
--------
The sub-project belog to 3 logical groups:
- **shared models** code which is used both in the server and the client, mostly models which are sent over the wire,
cross compiled for Scala and ScalaJS. These are found in the **shared** project
- **server side application**, which publishes shared models over a Monix stream, wired into a  web-socket endpoint. This is found
in the **server** project. What it currently does is to randomly select from a sequence of chat messages and publish each selection every 
**interval**, see **server/app/engine/DataProducer.scala**. 
- **mobile application**, which plugs the web-socket endpoint exposed by the **server** into a Monix stream, processed to update the chats
UI on every chat pushed from the server. The mobile app is an HTML5 app, built on Ionic and therefore cross compilable to Android and 
iOS. The code for this application is found in the **app-js** and **app-jvm** projects. **app-jvm** contains code which
generates the HTML code for the Angular app, making use of ScalaTags (compiles Scala code to HTML). **app-js** contains 
the ScalaJS code for the Angular components and stream processing. 

To see the streams in action, once you start the application, go to the **Chats** tab and you should see the list grow :).

Using the project
-----------------
Firstly, you need to install the Ionic framework - [http://ionicframework.com/getting-started/](http://ionicframework.com/getting-started/)
 
To start development of the server application:
```
sbt 
project server
... all play commands
```
To start development of the mobile application:
```
sbt 
compileHtmlDevTask // compileHtmlProdTask for production
~fastOptJS
```

### Running locally ###
In **app-js/src/main/scala/mobile/DataConsumer.scala** change line 17 to
```
    val host = hostBrowser
```
To run the app you need to start the server
```
sbt 
project server
run
```
and then, to run the mobile app in the browser, type in a different terminal tab:
```
cd ionic
ionic serve
```
(see http://ionicframework.com/docs/guide/testing.html)

### Running in an emulator ###

#### Android ####

Firstly, you need to install an Android SDK, see [http://developer.android.com/sdk/index.html](http://developer.android.com/sdk/index.html).

In **app-js/src/main/scala/mobile/DataConsumer.scala** change line 17 to
```
    val host = hostEmulator
```
To run the app you need to start the server
```
sbt 
project server
run
```
and then, to run the mobile app in the browser, type in a different terminal tab:
```
cd ionic
ionic build android
ionic emulate android
```

#### iOS ####
See Ionic docs here [http://ionicframework.com/docs/guide/installation.html](http://ionicframework.com/docs/guide/installation.html).
You would also need to modify **app-js/src/main/scala/mobile/DataConsumer.scala** to have **hostEmulator** represent the IP
of localhost as the emulator sees it.
 
 
### Running on a device ### 

In **app-js/src/main/scala/mobile/DataConsumer.scala** change line 17 to
```
    val host = hostHeroku
```

Helpful information can be found here [http://ionicframework.com/docs/guide/testing.html](http://ionicframework.com/docs/guide/testing.html)

In order to run the mobile application on a device, you need first to deploy the server application somewhere where is accessible via 
the device's internet connection. What we use currently is Heroku, deploying there via the [Heroku sbt plugin](https://github.com/heroku/sbt-heroku).
Change line 52 in build.sbt to your Heroku app name. You can use the version deployed at **monifu-ionic-sample** but when 
changing the server code, you would need to deploy those in your own application.

The optimized JS version of the mobile app has to be created with following command

```
dist
```
and next, execute 
```
ionic run android
```
or
```
ionic run ios
```
to run the application on a connected device (which has to be connected :) )

For troubleshooting, please check [http://ionicframework.com/docs/guide/testing.html](http://ionicframework.com/docs/guide/testing.html)