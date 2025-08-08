package cdw.CooDashWeelley.EmployeeBook;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import exceptions.NotEnterDataException;
import org.springframework.stereotype.Service;
import service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    private final int maxSizeOfEmployeeList = 10;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
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

    @Override
    public Employee remove(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee removeEmployee = new Employee(firstName, lastName);
        if (employeeList.contains(removeEmployee)) {
            employeeList.remove(removeEmployee);
            return removeEmployee;
        } else {
            throw new EmployeeNotFoundException("employee not found");
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee findingEmployee = new Employee(firstName, lastName);
        if (!employeeList.contains(findingEmployee)) {
            throw new EmployeeNotFoundException("employee not found");
        } else {
            return findingEmployee;
        }
    }

    @Override
    public Collection<Employee> showAll() {
        return Collections.unmodifiableList(employeeList);
    }
}
