# Employees CSV to MySQL migration

## Team members
- Adam Lemdani
- Andrei Cravtov
- Dan Booth
- Daniel Nenov
- Lovedeep Saini

### Explanation

This program reads data from .csv files and stores each row as in an employee class so each row can be used as an certain element (Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary). Then all of the Employees are joined together in an Employee class.
### CSV Converter
```java
CSVConverter.convert("src/main/resources/EmployeeRecords1.csv", employees, err);
```
First the convert method is called using the parameters for the files path, an employees class that will store all the employees found from the file, and a List of all of the erroneous data found in the file.
```java
static void convert(String fileName, Employees employees, List<String> erroneous) {
        try (FileReader reader = new FileReader(fileName)) {
        BufferedReader br =new BufferedReader(reader);
        br.readLine(); // skips 1st header line
```
Then a file reader is called and reads from the files path. The buffered reader is called next using the file reader to make things for efficient. The buffered reader skips the first line as the first line of each csv file just contains a header.
```java
  Employee employee;
  while ((line = br.readLine()) != null) {
      try {
          String[] elements = line.strip().split(",");
          if (!RecordValidator.isRecordValid(elements)) throw new IllegalArgumentException("This record is corrupt");
          String[] birthDateElems = elements[7].split("/");
          String[] joinDateElems = elements[8].split("/");
          employee = new Employee(
                   Integer.parseInt(elements[0]),
                   elements[1],
                   elements[2],
                   elements[3].charAt(0),
                   elements[4],
                   elements[5].charAt(0),
                   elements[6],
                   LocalDate.of(Integer.parseInt(birthDateElems[2]), Integer.parseInt(birthDateElems[0]), Integer.parseInt(birthDateElems[1])),
                   LocalDate.of(Integer.parseInt(joinDateElems[2]), Integer.parseInt(joinDateElems[0]), Integer.parseInt(joinDateElems[1])),
                   Integer.parseInt(elements[9])
          );
```
Each row of the buffer is read one at a time and each row is split up by each comma as each row in a csv is always separated by one. Each row is added to a Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary in order. Emp ID and Salary take an int since they are a number. Gender and Initial take a character since they just are one letter long. Date of birth and date joined take a type date. The employee is then added to the employees class.
```java
if (!RecordValidator.isRecordValid(elements)) throw new IllegalArgumentException("This record is corrupt");
```
If the data is corupted then it will throw an IllegalArgumentException to add it to the erroneous data.
```java
if (!employees.addEmployee(employee)) throw new IllegalArgumentException("Duplicate data");
} catch (IllegalArgumentException e) {
erroneous.add(line);
}
}
} catch (Exception e) {
throw new RuntimeException(e);
}
}
```
If a value catches an error with a row of data at any point such as an id including letters then it will add that row to the erroneous data to separate it from the employees class.
```java
public boolean addEmployee(Employee employee) {
if (employee == null) return false;

        Employee out = employees.putIfAbsent(employee.getId(), employee);
        if (out == null) return true;
        if (out.equals(employee)) return false;
        List<Employee> sorted = getSortedEmployees();
        return addEmployee(new Employee(
                sorted.get(sorted.size()-1).getId() + 1,
                employee.getNamePrefix(),
                employee.getFirstName(),
                employee.getMiddleInitial(),
                employee.getLastName(),
                employee.getGender(),
                employee.getEMail(),
                employee.getDateOfBirth(),
                employee.getDateOfJoining(),
                employee.getSalary()
        ));
    }
```
When a group of employees is added to an Employees object this is called to assign each element (Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary).
```java
@Override
public String toString() {
StringBuilder builder = new StringBuilder("Employees(\n");
for (Employee employee: getSortedEmployees())
builder.append("\t").append(employee.toString().replace("\n", "\n\t")).append(",\n");
return builder.deleteCharAt(builder.length()-2).append(")").toString();
}
```
Since an object already has an .toStrings() methods we need to override it. Each employee is called one by one and so are their elements so each can get displayed in a neat format.

```java
public Employee(
int employeeID,
@NotNull String namePrefix,
@NotNull String firstName,
char middleInitial,
@NotNull String lastName,
char gender,
@NotNull String eMail,
@NotNull LocalDate dateOfBirth,
@NotNull LocalDate dateOfJoining,
int salary
) {
super(employeeID);
this.namePrefix = namePrefix;
this.firstName = firstName;
this.middleInitial = middleInitial;
this.lastName = lastName;
this.gender = gender;
this.eMail = eMail;
this.dateOfBirth = dateOfBirth;
this.dateOfJoining = dateOfJoining;
this.salary = salary;
}
```
Instantiates a new employee object, taking the data as parameters\
@param employeeID the employee ID\
@param namePrefix the title of the employee\
@param firstName the first name of the employee\
@param middleInitial the middle name initial of the employee\
@param lastName the last name of the employee\
@param gender a character representing the gender of the employee\
@param eMail the email of the employee\
@param dateOfBirth the date of birth of the employee\
@param dateOfJoining the date of joining to the company of the employee\
@param salary the salary of the employee
```java
public String getNamePrefix() {
return namePrefix;
}

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEMail() {
        return eMail;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }
```
Each part of an Employee can be gotten by calling each of these get methods.
## Phase 2

### Program Flow

Write SQL Statements to create table and persist data to the table.
```SQL
CREATE TABLE `sparta_db`.`employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_prefix` VARCHAR(5) NOT NULL,
  `first_name` VARCHAR(60) NOT NULL,
  `middle_name_initial` CHAR(1) NOT NULL,
  `last_name` VARCHAR(60) NOT NULL,
  `gender` CHAR(1) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `date_birth` DATETIME NOT NULL,
  `date_joined` DATETIME NOT NULL,
  `salary` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
```
Create  DB connection .

Create an Interface for Data Access Object to persist the data in database.

```java
package com.sparta.dao.interfaces;

import com.sparta.entities.DataObject;

import java.util.List;

public interface DAO<T extends DataObject> {
    int insert(T newRow);
    void update(T updatedRow);
    T findById(int id);
    List<T> findAll();
    void deleteById(int id);
    void deleteAll();
}


```
### CRUD Operations
In order to interact with the database,  Data Access Object interface created, containing methods to create a table and insert into it, as well as selecting and printing it out.

A Data Transfer Object was used to store the data from the CSV file in a compatible format for the database.
#### Prepared statements are used to access db. SELECT statement is used to select data from database.
#### Code retrieve particular (id) record from the database.
```java
    @Override
    public Employee findById(int id) {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement find;
        Employee result;
        try {
            find = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            find.setInt(1, id);
            ResultSet rs = find.executeQuery();
            rs.next();
            result = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4).charAt(0),
                    rs.getString(5),
                    rs.getString(6).charAt(0),
                    rs.getString(7),
                    rs.getDate(8).toLocalDate(),
                    rs.getDate(9).toLocalDate(),
                    rs.getInt(10)
            );
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return result;
    }
```
#### INSERT INTO statements used to insert new records in a table.


```java
    public int insert(Employee newRow) {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement insert;
        int newId = newRow.getId();
        try {
            try {
                insert = conn.prepareStatement(
                        "INSERT INTO employees(id, name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setInt(1, newRow.getId());
                insert.setString(2, newRow.getNamePrefix());
                insert.setString(3, newRow.getFirstName());
                insert.setString(4, String.valueOf(newRow.getMiddleInitial()));
                insert.setString(5, newRow.getLastName());
                insert.setString(6, String.valueOf(newRow.getGender()));
                insert.setString(7, newRow.getEMail());
                insert.setDate(8, Date.valueOf(newRow.getDateOfBirth()));
                insert.setDate(9, Date.valueOf(newRow.getDateOfJoining()));
                insert.setInt(10, newRow.getSalary());
                insert.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                insert = conn.prepareStatement(
                        "INSERT INTO employees(name_prefix, first_name, middle_name_initial, last_name, gender, email, date_birth, date_joined, salary)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setString(1, newRow.getNamePrefix());
                insert.setString(2, newRow.getFirstName());
                insert.setString(3, String.valueOf(newRow.getMiddleInitial()));
                insert.setString(4, newRow.getLastName());
                insert.setString(5, String.valueOf(newRow.getGender()));
                insert.setString(6, newRow.getEMail());
                insert.setDate(7, Date.valueOf(newRow.getDateOfBirth()));
                insert.setDate(8, Date.valueOf(newRow.getDateOfJoining()));
                insert.setInt(9, newRow.getSalary());
                insert.executeUpdate();

                ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID(id) FROM employees ORDER BY LAST_INSERT_ID(id) DESC LIMIT 1");
                rs.next();
                newId = rs.getInt(1);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return newId;
    }

```
#### Code retrieve all the records of employees from database.
```java
    @Override
    public List<Employee> findAll() {
        // get connection
        Connection conn = connPool.borrowConnection();

        PreparedStatement find;
        ResultSet rs;
        List<Employee> result = new ArrayList<>();
        try {
            find = conn.prepareStatement("SELECT * FROM employees");
            rs = find.executeQuery();
            while (rs.next()) {
                result.add(new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4).charAt(0),
                        rs.getString(5),
                        rs.getString(6).charAt(0),
                        rs.getString(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getDate(9).toLocalDate(),
                        rs.getInt(10)
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);

        return result;
    }
```
TRUNCATE TABLE deletes the data inside a table, but not the table itself.
```java
    @Override
    public void deleteAll() {
        // get connection
        Connection conn = connPool.borrowConnection();

        try { conn.createStatement().execute("TRUNCATE employees"); }
        catch (SQLException e) { throw new RuntimeException(e); }

        // return connection
        connPool.returnConnection(conn);
    }
```

### Phase 3 - Concurrency 
Phase 3 of the product speification was the requirement to incorporate concurrency 
into our project. With the structure of our project being tailored to performance, the most logical
implementation of concurrency was while inserting data into our database.
This being on the basis that it enables us to act upon multiple insertions, as opposed 
to being restricted by a sequential addition. 

In order to facilitate this we created a <b><i> ConnectionPool </b></i> class, which
is responsible for instantiating new connections, as well as assigning the connections to threads.

```java
    private static ConnectionPool instance;

    private static final int POOL_CAPACITY = 25;

    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(POOL_CAPACITY);
```
As the above snippet demonstrates, 3 instance variables were declared which served the following purpose:
- <b> instance </b> - Instantiates new <b> ConnectionPool </b> object. 
- <b> POOL_CAPACITY </b> - Specifies how many threads are in use 
- <b> pool </b> - This serves the following purposes: 
    
    - Provides a thread-safe collection, ensuring data integrity and reliable functionality through ArrayBlockingQueue.
    - A means of storing connections 

The constructor of course serving as means of constructing our connections, is also delegated 
the task of screening and instantiating the specified number of threads declared through
<b>POOL_CAPACITY</b>

```java
    private ConnectionPool() {
        String url;
        String username;
        String password;

        // get properties
        Properties props = new Properties();
        try {
            props.load(new FileReader("src/main/resources/dbconnect.properties"));
            url = props.getProperty("mysql.url");
            username = props.getProperty("mysql.username");
            password = props.getProperty("mysql.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // initialize connections
        try {
            for (int i=0; i<POOL_CAPACITY; i++)
                pool.offer(DriverManager.getConnection(url, username, password));
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
```

Following on from the successful construction of our <b> connection </b>, we then proceed 
to ensure that we have a viable connection, which is achieved by screening to check if our 
<b> instance </b> is <i> null </i> and in the event it is, instantiates a nwe connection.


```java
    static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }
```
Once connections are assigned, they'll fulfill their delegated task before becoming obsolete and as such, should
be returned to the <b> pool </b> for re-allocation, so that they can be used again going forward.
In the snippet below, we're iterating through our <b> pool </b> and attempting to obtain the first connection stored,
in the event this returns <i> null </i>, we pause until one becomes available before returning the fresh connection.

```java
    Connection borrowConnection() {
        Connection conn;
        while ((conn = pool.poll()) == null); // pause execution until connection obtained
        return conn;
    }
```
In the above snippet, there's a situation in which the pool may run dry, where all connections are in use and there's none
available for re-allocation - This is alleviated largely through usage of returning our connections following having completed
their intended use-case.

```java
    void returnConnection(Connection connection) {
        pool.offer(connection);
    }
```
The above method is called within the <b> EmployeeDAO </b> class which is explained and demonstrated in <b><i> Phase 2 </b></i>
above.
What this method does is return the connection back into the pool following its use case having been completed, which ensures 
a replenishment in the pool alleviating circumstances in which a connection is required, but not available.


### Phase 4
A lambda expression is a short block of code which takes in parameters and returns a value. Lambda expressions are similar to methods, but they do not need a name, and they can be implemented right in the body of a method.
A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.
### Introducing Functional Programming( Lambda Expressions and Streams) in code.
```java
public class FunctionalCSVConverter {
    static void convert(String fileName, Employees employees, List<String> erroneous) throws IOException {

        Files
                .lines(Path.of(fileName)) // Calling from CSV as a stream
                .skip(1) // skips first line which is the heading - Not valid data
                .filter(s -> { // remove corrupted data
                    if (RecordValidator.isRecordValid(s.split(","))) return true;
                    erroneous.add(s);
                    return false;
                })
                .map(s -> s.split(",")) // splits on comma
                .map(s -> { // creates employee objects
                    String[] birthDateElems = s[7].split("/");
                    String[] joinDateElems = s[8].split("/");

                    return new Employee(
                        Integer.parseInt(s[0]),
                        s[1],
                        s[2],
                        s[3].charAt(0),
                        s[4],
                        s[5].charAt(0),
                        s[6],
                        LocalDate.of(Integer.parseInt(birthDateElems[2]), Integer.parseInt(birthDateElems[0]), Integer.parseInt(birthDateElems[1])),
                        LocalDate.of(Integer.parseInt(joinDateElems[2]), Integer.parseInt(joinDateElems[0]), Integer.parseInt(joinDateElems[1])),
                        Integer.parseInt(s[9])
                    );
                })
                .forEach(e -> {
                    if (!employees.addEmployee(e))
                        erroneous.add(String.format(
                                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                                e.getId(),
                                e.getNamePrefix(),
                                e.getFirstName(),
                                e.getMiddleInitial(),
                                e.getLastName(),
                                e.getGender(),
                                e.getEMail(),
                                e.getDateOfBirth().format(DateTimeFormatter.ofPattern("d/MM/uuuu")),
                                e.getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/uuuu")),
                                e.getSalary()
                        ));
                });
    }
}
```


## Conclusion

<b> Concurrency </b>

Concurrency served as a solid implementation, enhancing the overall performance of our insertion to our database.
Within our group, we have 3 different operating systems in use, representing windows, linux and macOS. As a result of this,
and different hardware availability, we experienced varying results between us. These being as follows:

- Mac OS performed better with  fewer threads, inserting 10k records in 2.4 seconds while utilising 25 threads and depleting when going beyond.
- Linux performed better with more threads, inserting 10k records in 3.5 seconds while utilising 150 threads and depleting when going lower.
- Windows performed between the two, inserting 10k records in 3.7 seconds with 100 threads and depleting when going above, or below.

Research has revealed that within Linux thread usage is cheaper, with Windows coming in second and MacOS third. This being one driving 
influence behind the results.

On average, concurrency enhanced performance quite dramatically, almost halving insertion times on 10k records.

<b> Functional Programming </b>

The initial stages of planning entailed a large focus and discussion relating to performance, with a focus on avoiding
and alleviating unecessary/avoidable time complexities, whether this be through collections used, methods used or otherwise.
As a result, we believe that there wasn't much room for expansion, or improvement in regards to efficacy which may have influenced
our results into seeming <i> lesser </i> than they actually are.

Overall, we found the following:

While comparing the <b> FunctionalCSVReader </b> to its non-functional counterpart, extensive testing revealed that it was infact less-performant,
averaging around 100ms slower across all operating systems and machines available to us.

In this application, it seemed that the incorporation of functional programming actually slowed things down. Again, this is possibly
attributed to the fact that we as a team placed a large focus on speed and efficacy, which unfortunately functional programming 
can't facilitate. Although providing consistently reliable execution, this seemingly comes at the price of lack of customisability 
and if speed is the intention, could potentially serve as a bottleneck and counter-intuitive implementation.
