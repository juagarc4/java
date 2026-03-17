# JAVA

This repository contains all apps created during is the refresh of my Java knowledge.
They were built during the course "Universidad Java - Cero a Experto - Actualizado (+155 hrs)"
from Global Mentoring: Ing. Ubaldo Acosta and Ing. Marcela Gamiño
Udemy URL: https://www.udemy.com/share/101Wlw3@Z_HAS54ERKRDzTcb-KtiTPcpTuyzFSQ6qVNCd_ovGua8Wax-uO2f751JGlixuESt/

# Notes

Although the apps were built during the mentioned course. More of them contain more enterprise code, adding error
handling and some other best practices followed by the industry.

### Modules

* sales: Small app simulating orders and products to practice OOP concepts like encapsulation or polymorphism.
* varargs: Refreshing variable arguments.
* advancedconcepts: Using Collections like Lists, Maps and Sets
* snacksmachine: Small app simulating a snack machine to practice the use of collections.
* snacksmachine2: Small app simulating a snack machine to practice the use of collections. This version uses a
  multilayer architecture and persistent data using files.
* files: Practicing to read and write files.
* zone_fit: Small app using Database connections with JDBC and MySql. It uses Maven to manage dependencies.
* zone_fit_spring: The same application zone_fit, but using Spring, Lombok and JPA with Hibernate.
* swing: Test of Swing UI. Login Form.
* zone_fit_spring_swing: The same application zone_fit_spring, but using Swing for the user interface, Lombok and JPA
  with Hibernate.
* zone_fit_spring_web_jsf: The application zone_fit_spring converted to web application, but using  
  JSF and PrimeFaces.
* tasks_javafx: Task application made with Spring Boot and JavaFX.
* employees: Employees System application using Spring Boot and JSP.
* contacts: Contacts System application using Spring Boot, JPA and Thymeleaf.

# Database

A docker-compose.yml file is provided to provision a MariaDB database in a docker container.  
Run **docker compose up -d**  to start the database.

Use the url http://localhost:8080/ to access the admin interface (adminer) to manage the databases.

The app will create a new database tasks_db if it doesn't exist.

Adminer port: 8081
MariaDB port: 3306