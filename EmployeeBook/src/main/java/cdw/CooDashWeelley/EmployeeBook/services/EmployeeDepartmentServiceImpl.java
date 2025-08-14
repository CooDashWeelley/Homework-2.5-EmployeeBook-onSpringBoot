package cdw.CooDashWeelley.EmployeeBook.services;

import cdw.CooDashWeelley.EmployeeBook.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {
    private final EmployeeServiceImpl service;
    private List<Employee> employeeList;

    public EmployeeDepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.service = employeeService;
    }


    @Override
    public Employee minSalaryInDepartment(int department) {
        return employeesInDepartment(department).stream()
                .min(Comparator.comparing(e -> e.getSalary()))
                .get();
    }

    @Override
    public Employee maxSalaryInDepartment(int department) {
        return employeesInDepartment(department).stream()
                .max(Comparator.comparing(e -> e.getSalary()))
                .get();
    }

    @Override
    public int monthSalaryInDepartment(int department) {
        return employeesInDepartment(department).stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    @Override
    public int averageSalaryInDepartment(int department) {
        return monthSalaryInDepartment(department) / employeesInDepartment(department).size();
    }

    @Override
    public void indexSalaryInDepartment(int department, int index) {
        employeesInDepartment(department).stream()
                .map(e -> e.getSalary() / 100 * index)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> employeesInDepartment(int department) {
        return service.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployeesByDepartment() {
        return service.getEmployeeList().stream()
                .sorted(Comparator.comparing(e -> e.getDepartment()))
                .collect(Collectors.toList());
    }
}
