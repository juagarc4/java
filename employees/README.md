# Employees System

This is a small java app to manage an Employees System. This app is a web app using JSPs and Spring Boot.
It works as backend for the react frontend located in frontends/employees-react

It was built during the course "Universidad Java - Cero a Experto - Actualizado (+155 hrs)"
from Global Mentoring: Ing. Ubaldo Acosta and Ing. Marcela Gamiño
Udemy URL: https://www.udemy.com/share/101Wlw3@Z_HAS54ERKRDzTcb-KtiTPcpTuyzFSQ6qVNCd_ovGua8Wax-uO2f751JGlixuESt/

# Notes

Although the app was built during the mentioned course. It contains more enterprise code, adding error handling and
some other best practices followed by the industry.

# Database

A docker-compose.yml file is provided in the root of the java projects folder to provision a MariaDB database in a
docker container.  
Run **docker compose up -d**  to start the database.

Use the url http://localhost:8080/ to access the admin interface (adminer) to manage the databases.

1. A new database "employees_db" will be created automatically, if it doesn't exist.
2. The table employees will be created automatically.