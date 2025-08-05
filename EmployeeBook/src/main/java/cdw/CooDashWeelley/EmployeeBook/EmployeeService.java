package cdw.CooDashWeelley.EmployeeBook;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>() ;
    int maxSizeOfEmployeeList = 10;

    public void addEmployee (String firstName, String lastName) {
        if (employeeList.size() == maxSizeOfEmployeeList) {
            throw new EmployeeStorageIsFullException("storage is full");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee Already Added");
        }
        employeeList.add(employee);
    }

    public void removeEmployee(String firstName, String lastName) {
        if (true) {

        };
    }
}
