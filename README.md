# StudentManagement

## Dotenv-java 

- Using dotenv-java to load .env file that contains secret information about a database.
- The format of .env is `key=value` pair. Example: `MY_ENV_VAR1=value`

````java
Dotenv dotenv = Dotenv.load();
dotenv.get("MY_ENV_VAR1"); // return the value of MY_ENV_VAR1
````

- <a href="https://github.com/cdimascio">**Author: Carmine DiMascio**</a>
- <a href="https://github.com/cdimascio/dotenv-java">**Repository: Dotenv-java**</a>
 
---

## JDBC (Java Database Connectivity)

### JDBC Architecture
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20250117153514606749/JDBC-Architecture.webp" alt="jdbc architecture">

For more details: <a href="https://www.geeksforgeeks.org/java/introduction-to-jdbc/">Geeks For Geeks</a>

### JDBC Working flow

<img src="https://images.viblo.asia/full/31d58a8f-02d8-4078-ae8d-33385ef6193b.png" alt="jdbc working flow">

### MySQL connector

To connect MySQL using JDBC, we need driver JDBC. Ensure to add dependency MySQL connector

- Maven
````xml
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.4.0</version>
    </dependency>
````

- Gradle

````groovy
// groovy
    dependencies {
        implementation 'mysql:mysql-connector-java:8.0.33'
    }
````

````kotlin
// kotlin dsl
    dependencies {
        implementation("mysql:mysql-connector-java:8.0.33")
    }
````

### Steps to Connect to MySQL Database Using JDBC (Type-4)
**- Step 1: Load the JDBC Driver**
````java
    Class.forName("com.mysql.cj.jdbc.Driver");
````

**- Step 2: Establish a Connection**
```java
    Connection connection = DriverManager.getConnection(
    
        "jdbc:mysql://localhost:3306/your_database",
    
        "your_username",
    
        "your_password"
    
    );
```

**- Step 3: Create a Statement**
````java
    Statement statement = connection.createStatement();
````
**- Step 4: Execute a Query**
```java
// Example
    String query = "INSERT INTO students (id, name) VALUES (101, 'John Doe')";
    
    int rowsAffected = statement.executeUpdate(query);
    
    System.out.println("Rows affected: " + rowsAffected);
```

**Step 5: Close the Connection**
```java
    statement.close();
    connection.close();
```




