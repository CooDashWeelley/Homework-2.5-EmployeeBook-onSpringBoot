package cdw.CooDashWeelley.EmployeeBook.services;

import cdw.CooDashWeelley.EmployeeBook.Employee;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeAlreadyAddedException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeNotFoundException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeStorageIsFullException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.NotEnterDataException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    private int maxSizeOfEmployeeList = 10;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (firstName == null || lastName == null || salary == null || department == null) {
            throw new NotEnterDataException("not enter data");
        }
        if (employeeList.size() == maxSizeOfEmployeeList) {
            throw new EmployeeStorageIsFullException("storage is full");
        }
        Employee addingEmployee = new Employee(firstName, lastName, salary, department);
        if (employeeList.contains(addingEmployee)) {
            throw new EmployeeAlreadyAddedException("Employee Already Added");
        }
        employeeList.add(addingEmployee);
        return addingEmployee;
    }

    @Override
    public Employee remove(String firstName, String lastName, Integer salary, Integer department) {
        if (firstName == null || lastName == null || salary == null || department == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee removeEmployee = new Employee(firstName, lastName, salary, department);
        if (employeeList.contains(removeEmployee)) {
            employeeList.remove(removeEmployee);
            return removeEmployee;
        } else {
            throw new EmployeeNotFoundException("employee not found");
        }
    }

    @Override
    public Employee find(String firstName, String lastName, Integer salary, Integer department) {
        if (firstName == null || lastName == null || salary == null || department == null) {
            throw new NotEnterDataException("not enter data");
        }
        Employee findingEmployee = new Employee(firstName, lastName, salary, department);
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

    @Override
    public int monthSalary() {
        return employeeList.stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    @Override
    public Employee minSalary() {
        return employeeList.stream()
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .get();
    }

    @Override
    public Employee maxSalary() {
        return employeeList.stream()
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .get();
    }

    @Override
    public int averageSalary() {
        return monthSalary() / employeeList.size();
    }

    @Override
    public void indexSalary(int index) {
        employeeList.stream()
                .map(e -> e.getSalary() / 100 * index)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> salaryLessThan(int amount) {
        return employeeList.stream()
                .filter(e -> e.getSalary() < amount)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> salaryMoreThan(int amount) {
        return employeeList.stream()
                .filter(e -> e.getSalary() > amount)
                .collect(Collectors.toList());
    }
}
