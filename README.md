# ZEDapp

That project contains REST application which is backend part of order management application.
Application allows us to manage orders, companies, customers and all documents which relate to our orders.
Data is collected in MySQL database. Application can create cnc programmes for standard elements, cad drawings,
pdf files and store that in specific place such as: Google drive, ftp server, local memory etc.


Technologies:
* Java 11
* MySQL/PostgreSQL(only for Heroku)
* Hibernate 5.4.10
* Spring-boot 2.2.4
* Lombok 1.18.10
* H2 database 1.4.200


## Demo

You can see demo version here: [Demo](https://zedapp.herokuapp.com/). 
This project does not contain frontend part, so you can test API by Postman.


## Requirements

To run the project you need:
* Intellij
* Java 11
* Gradle 6.0.1


## How to run it

Steps to run that project:


#### 1. Open intellij and create project from version control.


#### 2. Clean project

Linux/MacOS:
```
./gradlew clean
```

Windows:
```
gradlew clean
```


#### 3. Build project

Linux/MacOS:
```
./gradlew build
```

Windows:
```
gradlew build
```


#### 4. Run project

Linux/MacOS:
```
./gradlew bootRun
```

Windows:
```
gradlew bootRun
```


## Endpoints

Coming soon...


## Development

Coming soon...


## Troubleshooting

Coming soon...


## Authors

* **Tomasz Siwiec** - [TomaszSiwiec](https://github.com/TomaszSiwiec)

See also the list of [contributors](https://github.com/TomaszSiwiec/ZEDapp/graphs/contributors) who participated in this project.
