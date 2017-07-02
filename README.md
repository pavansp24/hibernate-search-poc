# hibernate-search-poc

Build and Run

Prerequisites

Java 8
Maven > 3.0

From terminal

Go on the project's root folder, then type:

$ mvn spring-boot:run
From Eclipse (Spring Tool Suite)

Import as Existing Maven Project and run it as Spring Boot App.

Usage :

Run the application

To Create Records

http://localhost:8080/search - POST

{
	"isbnum" : "833355588" ,
	"name" : "Java",
	"org"  : "Visa" ,
	"author" : "Pavan" ,
	"chapters" : [
					 {
					 	"cname" : "Introduction" ,
					 	"description" : "Gives Introduction"
					 } ,
					 {
					 	"cname" : "Chapter -1" ,
					 	"description" : "Basics"
					 }
		]
}

Type the url http://localhost:8080/search/Introduction 

[
    {
        "isbnum": 83388,
        "name": "Hibernate Search",
        "org": "Visa",
        "author": "Pavan",
    },
    {
        "isbnum": 83355588,
        "name": "Hibernate Validation",
        "org": "Visa",
        "author": "Pavan"
    },
    {
        "isbnum": 833355588,
        "name": "Java",
        "org": "Visa",
        "author": "Pavan"
    }
]
