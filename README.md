# employee-csv




<h3>Explanation</h3>

This program reads data from .csv files and stores each row as in an employee class so each row can be used as an certain element (Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary). Then all of the Employees are joined together in an Employee class.
<h3>CSV Converter</h3>
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
String line;
final StringBuffer buffer = new StringBuffer(2048);
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
```
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

```
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
```
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


```
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
```
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
```
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
### Phase 4
A lambda expression is a short block of code which takes in parameters and returns a value. Lambda expressions are similar to methods, but they do not need a name, and they can be implemented right in the body of a method.
A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.
### Introducing Functional Programming( Lambda Expressions and Streams) in code.
```
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
#### Conclusion
When comparing our functional CSV reader, which utilises Lambdas and streams we came to conclusion that the functional implementation leads to a performance deficit resulting in around a 100ms performance reduction when compared to its non-functional counterpart.
This in part could be attributed to the freedom provided when not interacting with streams, as it enables the developer to fine-tune their code and implementation for performance as well as functionality.



