                                              
## __Case Study:  myRetail RESTful service__

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
•	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  
•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.   

*********************************************************************************************************************************
# __Solution:__

## __MyRetail API Solution provides the ability to:__

<ol>
  <li>Retrieve product and pricing information by Product Id.</li>
  <li>Update the price information in the mongo database.</li>
  <li>Secure API with basic authentication.</li>
  <li>One rest end point is not secure. /products just to show the how implementation without secure can be implemented</li>	
  <li>Implement Swagger2 for API documentation</li>
</ol>
All the end points are totally secure in this application. I have implemented basic security and method level security as well. Update resource can be accessed by admin/admin user only.

                                   Method               Request                   Credentials
                                     GET              /products/{id}              [SECURE -- admin/admin]
                                     PUT              /products/{id}              [SECURE -- admin/admin]
					                 GET              /products                   [NOT SECURE]

###### __Technology Stack:__

1. Spring Boot : 
2. Feign: Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.
3. MongoDB:
4. Maven:
5. Mokito/Junit:
6. Postman: for testing the secured services

###### __Setup instructions:__

1. Java 1.8
2. Eclipse
3. Install Mongo DB
4. Install Maven 
5. Download project
	a) Download as a ZIP file OR
6. Import the project into eclipse –   File->import

##### Run Mongo

Run the Mongo daemon, in one of your terminal windows run "mongod". This should start the Mongo server.
Run the Mongo shell, with the Mongo daemon running in one terminal, type "mongo" in another to run the Mongo shell

###### __Test the project:__

Test cases are present on the following directory. I have written some test cases for controller class and service class using mokito. I am using mokito for mockdata.

C:\WORK_ENV\workspace\myRetail\src\test\java

To run the test Go to project folder and trigger following command on the command prompt or you can also right click on the project click "Run As" > "Maven Test" (Make sure that your are running the mongodb before running the test cases or the application)

mvn test.

###### __To run the application:__

Run mongo DB from the command prompt.  And test  ---  http://localhost:27017/  (default port)
Go to the project folder and trigger the command:

mvn spring-boot:run 

###### __Check the http Request:__

### Secure API
The end point of this application is fully secure. There are 3 users in this application.
1. admin/admin   --- Can update price information and get the product by prodctId. 
2. normaluser/normaluser  --  get the product by prodctId.
3. dbuser/dbuser  -- get the product by prodctId.

###  Swagger2 documentation path
http://localhost:8080/swagger-ui.html

Some of the requests that could be peformed.
GET: With valid product but no credentials (http://localhost:8080/products/13860428)
Response:

```
Response Status Code: 401 Unauthorized
Response Body:
 Http Status 401 Bad Credentials. 

```


GET: with valid product and admin credentials (http://localhost:8080/products/13860428)

```
Response status: 200K
Response Body:
{
	"productId": 13860428,
	"name": "The Big Lebowski (Blu-ray) (Widescreen)",
	"current_price": {
		"value": 40.24,
		"currency_code": "USD"
	}
}

```


GET: Wrong product ID and valid credentials admin/admin (http://localhost:8080/products/13860428)

```
Response Status Code: 404 Not Found
Response Body:
{
    "timestamp": 1525289857658,
    "status": 404,
    "error": "Not Found",
    "exception": "com.myretail.exception.ResourceNotFoundException",
    "message": "Resource not found. ",
    "path": "/products/1386042674"
}
```


PUT Request: With Valid product Id and admin/admin credentials  (http://localhost:8080/products/13860428)

```
Request Body:
{
	"productId": 13860428,
	"name": "The Big Lebowski (Blu-ray) (Widescreen)",
	"current_price": {
		"value": 40.24,
		"currency_code": "USD"
	}
}
Response Status Code: 200 OK
Response Body: 
{
    "value": 200,
    "message": "Product price has been updated"
}

```


PUT Request: With Valid product Id and normaluser/normaluser credentials  (http://localhost:8080/products/13860428)

```
Response Status Code: 401 Unauthorized
Response Body:
 Http Status 401 Bad Credentials. 
```



