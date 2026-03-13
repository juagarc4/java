# Tasks Spring JavaFX

This is a simple tasks system built using Spring Boot and JavaFX
interface.

It was built during the course "Universidad Java - Cero a Experto - Actualizado (+155 hrs)"
from Global Mentoring: Ing. Ubaldo Acosta and Ing. Marcela Gamiño
Udemy URL: https://www.udemy.com/share/101Wlw3@Z_HAS54ERKRDzTcb-KtiTPcpTuyzFSQ6qVNCd_ovGua8Wax-uO2f751JGlixuESt/

# Database

A docker-compose.yml file is provided to provision a MariaDB database in a docker container.  
Run **docker compose up -d**  to start the database.

Use the url http://localhost:8080/ to access the admin interface (adminer) to manage the databases.

The app will create a new database `tasks_db` if it doesn't exist.
A table `tasks` will be crated automatically if it doesn't exist.