package cdw.CooDashWeelley.EmployeeBook;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import exceptions.NotEnterDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>() ;
    int maxSizeOfEmployeeList = 10;

    public Employee addEmployee (String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NotEnterDataException("not enter data");
        }
        if (employeeList.size() == maxSizeOfEmployeeList) {
            throw new EmployeeStorageIsFullException("storage is full");
        }
        Employee addingEmployee = new Employee(firstName, lastName);
        if (employeeList.contains(addingEmployee)) {
            throw new EmployeeAlreadyAddedException("Employee Already Added");
        }
        employeeList.add(addingEmployee);
        return addingEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee removeEmployee = new Employee(firstName, lastName);
        for (Employee employee : employeeList) {
            if (removeEmployee.equals(employee)) {
                employeeList.remove(removeEmployee);
                return removeEmployee;
            }
            if (!removeEmployee.equals((Employee) employee)) {
                throw new EmployeeNotFoundException("employee not found");
            }
        }
        return null;
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee findingEmployee = new Employee(firstName, lastName);
        for (Employee employee : employeeList) {
            if (!findingEmployee.equals((Employee) employee)) {
                throw new EmployeeNotFoundException("employee not found");
            } else {
                return findingEmployee;
            }
        }
        return null;
    }

    public Employee showAllEmployees () { // доделать, не работает
        return (Employee) employeeList;
    }
}
