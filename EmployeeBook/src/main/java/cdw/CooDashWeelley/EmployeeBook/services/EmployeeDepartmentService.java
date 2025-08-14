package cdw.CooDashWeelley.EmployeeBook.services;

import cdw.CooDashWeelley.EmployeeBook.Employee;

import java.util.Collection;

public interface EmployeeDepartmentService {

    Employee minSalaryInDepartment(int department);

    Employee maxSalaryInDepartment(int department);

    int monthSalaryInDepartment(int department);

    int averageSalaryInDepartment(int department);

    void indexSalaryInDepartment(int department, int index);

    Collection<Employee> employeesInDepartment(int department);
}
