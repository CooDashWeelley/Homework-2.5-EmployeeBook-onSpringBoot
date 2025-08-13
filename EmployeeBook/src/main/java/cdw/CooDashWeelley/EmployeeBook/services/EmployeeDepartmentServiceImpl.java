package cdw.CooDashWeelley.EmployeeBook.services;

import cdw.CooDashWeelley.EmployeeBook.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {
    private EmployeeServiceImpl service;
    private Map<String, Employee> employeeMap;

    public EmployeeDepartmentServiceImpl (EmployeeServiceImpl employeeService) {
        this.service = employeeService;
    }

    public Map<String, Employee> employeeMap() {
        Map<String, Employee> employeeMap = new HashMap<>();
    }
    @Override
    public Employee minSalaryInDepartment(int department) {
        int minSalaryInDepartment = 0;
        int idOfMinSalaryInDepartment = 0;
        for (int i = 0; i < this.employeeMap.size(); i++) {
            if (this.employeeMap.get(i) != null && this.employeeMap.get(i).getDepartment() == department && minSalaryInDepartment == 0) {
                minSalaryInDepartment = this.employeeMap.get(i).getSalary();
            }
            if (this.employeeMap.get(i) != null && this.employeeMap.get(i).getDepartment() == department && minSalaryInDepartment >= this.employeeMap.get(i).getSalary()) {
                minSalaryInDepartment = this.employeeMap.get(i).getSalary();
                idOfMinSalaryInDepartment = i;
            }
        }
        return this.employeeMap.get(idOfMinSalaryInDepartment);
    }

    @Override
    public Employee maxSalaryInDepartment(int department) {
        int maxSalaryInDepartment = 0;
        int idOfMinSalaryInDepartment = 0;
        for (int i = 0; i < this.employeeMap.size(); i++) {
            if (this.employeeMap.get(i) != null && this.employeeMap.get(i).getDepartment() == department && maxSalaryInDepartment == 0) {
                maxSalaryInDepartment = this.employeeMap.get(i).getSalary();
            }
            if (this.employeeMap.get(i) != null && this.employeeMap.get(i).getDepartment() == department && maxSalaryInDepartment <= this.employeeMap.get(i).getSalary()) {
                maxSalaryInDepartment = this.employeeMap.get(i).getSalary();
                idOfMinSalaryInDepartment = i;
            }
        }
        return this.employeeMap.get(idOfMinSalaryInDepartment);
    }

    @Override
    public int monthSalaryInDepartment(int department) {
        int sum = 0;
        for (Employee employee : this.employeeMap) {
            if (employee != null && employee.getDepartment() == department) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    @Override
    public int averageSalaryInDepartment(int department) {
        int amountOfEmployeeInDepartment = 0;
        for (Employee employee : this.employeeMap) {
            if (employee != null && employee.getDepartment() == department) {
                amountOfEmployeeInDepartment++;
            }
        }
        return monthSalaryInDepartment(department) / amountOfEmployeeInDepartment;
    }

    @Override
    public void indexSalaryInDepartment(int department, int index) {
        for (Employee employee : this.employeeMap) {
            if (employee != null && employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() + employee.getSalary() / 100 * index);
            }
        }
    }

    @Override
    public Collection<Employee> employeesInDepartment(int department) {
        List<Employee> employeesInDepartmentList = new ArrayList<>();
        for (Employee employee : this.employeeMap) {
            if (employee != null && employee.getDepartment() == department) {
                employeesInDepartmentList.add(employee);
            }
        }
        return employeesInDepartmentList;
    }
}
