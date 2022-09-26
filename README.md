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

