# Employee-Management-System
An **Employee Management System** built using **Java**, **Spring Boot**, **Hibernate**, and **MySQL**. The application allows for the management of employees, departments, and roles, offering CRUD (Create, Read, Update, Delete) functionality. It provides a secure and efficient way to manage organizational data.

## Features

- **Employee Management**: Add, update, delete, and view employee details.
- **Department Management**: Manage departments and assign employees to departments.
- **Role Management**: Create, assign, and manage roles for employees.
- **Secure Authentication**: Role-based authentication using Spring Security.
- **RESTful APIs**: Expose services through REST APIs for integration and external usage.
- **Database Integration**: MySQL database for data persistence.

## Technologies Used

- **Java 17** (or your preferred version)
- **Spring Boot 2.x**
- **Spring Security** (for authentication and authorization)
- **Hibernate/JPA** (for ORM and database integration)
- **MySQL** (as the database)
- **Postman** (for testing APIs)
- **Maven** (for project management and dependencies)

## Setup Instructions

### Prerequisites

- Java 17 or higher installed
- MySQL installed and running
- Maven installed for project dependencies

### Steps to Run the Application Locally

1. **Clone the repository**:

    ```bash
    git clone https://github.com/Deepak3334091/Employee-Management-System.git
    ```

2. **Set up MySQL Database**:
   
    Create a new database in MySQL (e.g., `employee_management_system`).

    ```sql
    CREATE DATABASE employee_management_system;
    ```

3. **Configure application.properties**:

    Open `src/main/resources/application.properties` and set the following database properties:

    ```properties
    spring.application.name=Employee Management System
    spring.datasource.url=jdbc:mysql://localhost:3306/employee_management_system  # Updated DB name
    spring.datasource.username=root
    spring.datasource.password=root  # Update with your MySQL password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.devtools.restart.enabled=true
    server.port=8082  # Updated port
    debug=true
    ```

    Ensure you replace `root` with your MySQL username and password if different.

4. **Run the application**:

    In your project directory, run the following Maven command to start the application:

    ```bash
    mvn spring-boot:run
    ```

5. **Access the Application**:

    Once the application is running, you can access it through `http://localhost:8082`.

### Running Tests

To run unit and integration tests:

```bash
mvn test
