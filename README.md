# weather-app
Spring Boot app that pulls data from metaweather API (url: https://www.metaweather.com/api/). Starter project which was made with purpose to familiarize myself with spring, jwt, spring data, spring security, etc. Metaweather api offers weather information for major cities. With this app you can subscribe to desidered cities and receive weather information. 

## Requirements
* Application expects a MySQL database named 'weather' on localhost on port 3306. Settings can be changed in file 'src/main/resources/application.properties'. It communicates with database through Spring Data.
* Before starting you have to set up your email credentials in .properties file so that an application could send email notifications.
* Application doesn't offer any registration form for new users, for now they are hardcorded in script 'src/main/resources/users.sql' which should be executed after first application start. It will populate tables 'users' and 'roles' with predefined users. You can use user: 'user' with password: 'password' or user 'admin' with pw 'admin'.

## Features
1. Application offers following REST API endpoints (requires jwt token with each request except for /auth):
  * /auth (POST) - for user authentification where you need to send your username and password as json. It will return a new jwt token
  * /refresh - for refreshing jwt token
  * /city - get cities that user could subscribe to
  * /city/{id} - get details about city with id {id}
  * /city/{id}/subscribe (POST) - subscribes current user to this city
  * /user - get information about currenty logged in user
  * /user/subscriptions - get current user subscriptions
  * /all-weather-info - fetches weather information from meataweather api for all existing subscriptions in database
2. When application is started a new thread is started which periodically stores weather information for cities users subscribed to. Thread can be paused with 'SaverClient' app that connects to application on port 4000 by sending string "STOP A00". You can unpause it again by sending "START 10". Thread request are logged in database and can be viewed on web at '/tread-control.xhtml'. Thread fetches new weather infos every 1h by default which can also be changed in 'application.properties' file. SaverClient is a new maven project which is compiled into .jar file. It can be executed through command line(position yourself in root directory) with:
`java -jar saverClient/target/saverClient-1.0-SNAPSHOT-jar-with-dependencies.jar  localhost 4000 "STOP A00"`
3. Web part is created using JSF and Primefaces and consist of:
  * /login.xhtml - login page, user can authenticate with username and password
  * /thread-control.xhtml - you can view thread requests here
  * /index.xhtml - main page, you can view existing subscriptions, remove them or add new ones. Here you also see weather information which were fetched for your subscriptions.
  
  Currently logged in user is defined by sending a jwt token as an authorization header for REST endpoints, e.g.:
  'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTM0NDk1NTUyLCJpYXQiOjE1MzM4OTA3NTJ9.XBr1LbTkMeCczRlkiTl96Wfjao1uFdKoQz9cL2niJLTfYROOUHY1EhqaFRy-ImB4sGz828l0tnsS9ouCT9IQ_A'.
  If you are viewing through web interface, then after login, a new jwt token is created and stored in your cookies.
