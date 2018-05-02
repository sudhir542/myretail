## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=orders-test target/orders-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=orders-test"
```
* Check the stdout to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2018-03-11 19:38:51.694  INFO 42281 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8088 (http)
2018-03-11 19:38:51.706  INFO 42281 --- [           main] com.avenuecode.orders.OrdersApplication  : Started OrdersApplication in 11.659 seconds (JVM running for 12.362)
2018-03-11 19:38:51.706  INFO 42281 --- [           main] com.avenuecode.orders.OrdersApplication  : Running orders application.
2018-03-11 19:39:28.005  INFO 42281 --- [nio-8088-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-03-11 19:39:28.005  INFO 42281 --- [nio-8088-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-03-11 19:39:28.042  INFO 42281 --- [nio-8088-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 37 ms
```

## About the Service

The service is just a simple Orders Application REST services. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.avenuecode.orders.resource``` on **port 8088**. (see below)
 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports only JSON response; 
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations 

Here are some endpoints you can call:

## Orders Related Services
### Retrieve a list of orders

```
http://localhost:8088/orders

Response: HTTP 200
```

### Retrieve a specific order resource

```
http://localhost:8088/orders/{orderId}

Response: HTTP 200
```

### Retrieve orders based on search=status:shipped
This search condition will get the orders whose status has been shipped

```
http://localhost:8088/orders?search=status:shipped

Response: HTTP 200
```

### Retrieve orders based on search=discount>0
This search condition will get the orders that have discount greater than 0 or that has discounts

```
http://localhost:8088/orders?search=discount>0

Response: HTTP 200
```

### Retrieve orders based on search=products>2
This search condition will get the orders that have more than 2 products in it

```
http://localhost:8088/orders?search=products>2

Response: HTTP 200
```


## Products Related Services
### Retrieve a list of products

```
http://localhost:8088/products

Response: HTTP 200
```

### Retrieve a specific product resource

```
http://localhost:8088/products/{productId}

Response: HTTP 200
```

### Retrieve orders based on search=price>30
This search condition will get the products whose price is greater than 30

```
http://localhost:8088/products?search=price>30

Response: HTTP 200
```

### To disable information logging and have only error logging since we do not want to hit the production servers with information logs. 
Please make the below changes on application.yml file

```
logging:
  level:
    ROOT: ERROR
```

### To view Swagger 2 API docs

Run the server and browse to localhost:8088/swagger-ui.html

### To view your H2 in-memory datbase

The 'retail_order' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8088/h2-console (username is 'avenucode' with password as 'avenuecode'). Make sure you disable this in your production profiles. 





