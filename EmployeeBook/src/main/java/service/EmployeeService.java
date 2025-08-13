package service;

import cdw.CooDashWeelley.EmployeeBook.Employee;

import java.util.Collection;

public interface EmployeeService {


    Employee add(String firstName, String lastName, int salary, int department);

    Employee remove(String firstName, String lastName, int salary, int department);

    Employee find(String firstName, String lastName, int salary, int department);

    Collection <Employee> showAll ();

    int monthSalary();

    Employee minSalary();

    Employee maxSalary();

    int averageSalary();

    void indexSalary(int index);

    Employee minSalaryInDepartment(int department);

    Employee maxSalaryInDepartment(int department);

    int monthSalaryInDepartment(int department);

    int averageSalaryInDepartment(int department);

    void indexSalaryInDepartment(int department, int index);

    Collection<Employee> employeesInDepartment(int department);

    Collection<Employee> salaryLessThan(int amount);

    Collection<Employee> salaryMoreThan(int amount);
}
