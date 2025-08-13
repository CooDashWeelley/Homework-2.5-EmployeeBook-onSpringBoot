package cdw.CooDashWeelley.EmployeeBook.services;

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

    Collection<Employee> salaryLessThan(int amount);

    Collection<Employee> salaryMoreThan(int amount);
}
