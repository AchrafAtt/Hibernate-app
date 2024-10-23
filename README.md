
# Hibernate Application

This is a simple Hibernate-based application to manage entities such as **Salle** and **Machine**. It uses MySQL as the database, and follows a basic Java project structure with DAO, service layers, and test components.

## Project Structure

```
src
│
├───main
│   ├───java
│   │   └───org
│   │       └───example
│   │           ├───dao       # Data Access Objects for database interaction
│   │           ├───entities  # Hibernate entities (Salle, Machine)
│   │           ├───service   # Business logic services
│   │           ├───test      # Test classes for application logic
│   │           └───util      # Utility classes and helpers
│   └───resources
│       └───hibernate.cfg.xml  # Hibernate configuration file
└───test
    └───java                   # Unit tests for the application
```

## Technologies Used
- **Java 8+**
- **Hibernate 5.x**
- **MySQL**
- **JUnit (for testing)**
- **Maven** (for project build)

## Prerequisites

Make sure you have the following installed:

- JDK 8 or higher
- Maven
- MySQL (Running instance of MySQL with a database named `tp_hibernate`)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-repo/hibernate-app.git
cd hibernate-app
```

### 2. Configure MySQL

Ensure you have a MySQL database named `tp_hibernate`. You can create the database using the following command in the MySQL shell:

```sql
CREATE DATABASE tp_hibernate;
```

### 3. Setup `hibernate.cfg.xml`

Modify the `hibernate.cfg.xml` file under `src/main/resources` to set your MySQL database connection details:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/tp_hibernate?zeroDateTimeBehavior=convertToNull</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">your_password</property>
```

### 4. Build and Run the Application

Use Maven to build the project and resolve dependencies:

```bash
mvn clean install
```

To run the application, you can run any test class from `src/main/java/org/example/test/`.

### 5. Project Entities

The project contains two primary entities: **Salle** and **Machine**.

- **Salle**: Represents a room that contains multiple machines.
- **Machine**: Represents a machine assigned to a specific room (**Salle**).

### 6. Running Tests

JUnit test cases can be found in the `src/test/java` directory. You can run the tests using Maven:

```bash
mvn test
```

## Hibernate Configuration

The `hibernate.cfg.xml` file configures the database and Hibernate settings. Some of the key configurations are:

```xml
<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
<property name="hibernate.hbm2ddl.auto">update</property>
<property name="hibernate.show_sql">true</property>
<property name="hibernate.format_sql">true</property>
```

- `hibernate.dialect`: Specifies the SQL dialect for the database.
- `hibernate.hbm2ddl.auto`: Automatically updates the database schema. For production, it's recommended to change this to `validate` or `none`.
- `hibernate.show_sql`: Shows generated SQL queries in the console.

## How to Use

### Example Code to Insert a New Machine

Here is an example to demonstrate how you can add a new machine to a room (**Salle**) using the service layer:

```java
SalleService salleService = new SalleService();
MachineService machineService = new MachineService();

// Create a new Salle
Salle salle = new Salle("Room-101");
salleService.createSalle(salle);

// Add a machine to the salle
Machine machine = new Machine("Machine-001", new Date(), salle);
machineService.createMachine(machine);
```

### Queries

The project uses both **JPQL** and **Native Queries** for querying the `Machine` entity:

```java
// JPQL Query
List<Machine> machines = machineService.findBetweenDates(date1, date2);

// Native Query
List<Machine> machinesNative = machineService.findBetweenDatesNative(date1, date2);
```

## Contribution

Feel free to fork this repository and make contributions by opening a pull request.

## License

This project is licensed under the MIT License.
