# AdLister Project

AdLister is a Java-based web application for creating, viewing, and managing classified ad listings.

## Project Structure

The project is divided into the following main directories:

1. **src/main/java/com/codeup/adlister**: Contains all the Java source files for the project.
    - **models**: Contains the data models used in the project (Ad, User).
    - **controllers**: Contains the servlet controllers for managing the web requests.
    - **dao**: Contains the Data Access Object (DAO) interfaces and implementations for interacting with the database.
    - **util**: Contains utility classes for validation, password handling, and database seeding.

2. **src/main/webapp**: Contains the web application resources.
    - **WEB-INF**: Contains the JSP files for the views of the application.
        - **partials**: Contains reusable JSP components (head, navbar, footer).
        - **ads**: Contains JSP files related to ad views (view ad, search results, index, create ad).
    - **img**: Contains image resources used in the web pages.
    - **css**: Contains the CSS stylesheets for the web pages.

3. **pom.xml**: The project object model (POM) file for the Maven build system. It includes the project configuration, dependencies, and build plugins.

4. **migration.sql**: SQL script for setting up the initial database structure.

## Building and Running the Project


This will compile the Java classes, run any tests, and package the application into a .war file in the target directory.

To run the project, you will need a servlet container like Apache Tomcat. Deploy the .war file to Tomcat and start the server.

## Database Setup

You will need to set up a MySQL database for the application. The `migration.sql` file in the root directory contains the SQL commands to create the necessary tables. You may need to modify the database configuration details (like the database URL, username, and password) in the DAO classes.


You need to have Java and Maven installed on your machine to build and run this project. 

To build the project, navigate to the root directory of the project (where the `pom.xml` file is located) and run:

