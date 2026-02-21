# ZoneFit Spring Swing

This is a small java app to manage a Gym. This app does the same as zone_fit_spring but using Swing for the user
interface.

It was built during the course "Universidad Java - Cero a Experto - Actualizado (+155 hrs)"
from Global Mentoring: Ing. Ubaldo Acosta and Ing. Marcela Gamiño
Udemy URL: https://www.udemy.com/share/101Wlw3@Z_HAS54ERKRDzTcb-KtiTPcpTuyzFSQ6qVNCd_ovGua8Wax-uO2f751JGlixuESt/

# Database

A docker-compose.yml file is provided to provision a MariaDB database in a docker container.  
Run **docker composer up -d**  to start the database.

Use the url http://localhost:8080/ to access the admin interface (adminer) to manage the databases.

1. Create a new database "zone_fit" if it doesn't exist.
2. Add a new table, if it doesn't exist or if you want to replace your existent one, using the follow SQL COMMAND:

```
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) NOT NULL,
`last_name` varchar(45) NOT NULL,
`membership` int(11) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `membership_UNIQUE` (`membership`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```