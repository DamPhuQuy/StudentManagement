# StudentManagement

## OpenCSV (library) 

### Set up 

```xml 
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.9</version>
</dependency>
```

### To Bean or Not to Bean

Implement CSV-handling methods in two convenient ways:
- using the handy CSVReader and CSVWriter objects (for simpler operations)
- using CsvToBean to convert .csv files into beans (which are implemented as annotated plain-old-java-objects)

#### The CSVReader

Through the supplied `readAll()` and `readNext()` (read Line by Line) methods.

