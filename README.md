monix-ionic-sample
==================

Sample of a mobile client app / web application demonstrating the streaming of values over web-socket, using Monix on both ends.

This project is on [scalajs-ionic-starttabs](https://github.com/olivergg/scalajs-ionic-starttabs) by @olivergg for the mobile app
and on [monix-sample](https://github.com/monifu/monix-sample).

It consists of the following sub-projects:
- **server** the server side application, built with Play 2.4
- **shared** code shared between the server and the mobile application, cross compiled for Scala and ScalajS
- **app-jvm** the mobile client html code, compiled from Scala code using [scalatsg](https://github.com/lihaoyi/scalatags)
- **app-js** the mobile application, built on [Ionic](http://ionicframework.com/)

Using the project
=================
Firstly, you need to install the Ionic framework - [http://ionicframework.com/getting-started/](http://ionicframework.com/getting-started/)

To start development of the server side application:

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

To create the fully optimized javascript version of the mobile application (and to remove developement files (fastopt js, source maps, etc.)) :

```
dist
```
`dist` is just an alias to 
`";cleanOutputJS ;packageJSDependencies ;packageScalaJSLauncher ;compileHtmlProdTask ;fullOptJS"`

Then, you can use :

```
ionic run
```
to launch the application on a connected device