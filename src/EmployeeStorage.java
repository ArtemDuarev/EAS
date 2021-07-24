import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeStorage {
    public HashMap<Integer,Person> employees;
    private int employeeCount = 0;

    public EmployeeStorage(){}

    public EmployeeStorage(int employeeQuantity) {
        employees = new HashMap<>();
        for (int i = 0; i < employeeQuantity; i++) {
            employees.put(employeeCount++, Person.consoleRead(new Reader()));
        }
    }
    public EmployeeStorage(String fileName, boolean overWriteFlag) throws Exception{
        readStorageFromFile(fileName, overWriteFlag);
    }


    public String getEmployeeData(int employeeNumber){
        return employees.get(employeeNumber - 1).getDataByKey();
    }

    public Map.Entry<Integer,Person> getEmployeeEntry(int employeeNumber){
        for(var k : employees.entrySet()){
            if(k.getKey() == employeeNumber){
                return k;
            }
        }
        return null;
    }

    public int getEmployeeQuantity(){
        return employees.size();
    }

    public void removeEmployee(int employeeNumber) throws IndexOutOfBoundsException {
        employees.remove(employeeNumber - 1);
    }

    public void addEmployee(Person newEmployee){
        employees.put(employeeCount++, newEmployee);
    }

    public void writeStorageToFile() throws IOException{
        PrintStream printStream = new PrintStream("employee_storage.csv");
            for(int i = 0; i < employees.size(); i++) {
                printStream.println(employees.get(i).getPersonInfo());
            }
    }

    public void readStorageFromFile(String fileName, boolean overWriteFlag) throws Exception{
        Path path = Path.of(fileName);
        List<String> list = Files.readAllLines(path);

            if(overWriteFlag || employees == null)
                employees = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                employees.put(employeeCount++, new Person(list.get(i)));
            }
    }
}