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
    public Employee add(String firstName, String lastName, int salary, int department) {
        if (firstName == null || lastName == null) {
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
    public Employee remove(String firstName, String lastName, int salary, int department) {
        if (firstName == null || lastName == null) {
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
    public Employee find(String firstName, String lastName, int salary, int department) {
        if (firstName == null || lastName == null) {
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
        int sum = 0;
        for (Employee employee : this.employeeList) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    @Override
    public Employee minSalary() {
        int minSalary = this.employeeList.get(0).getSalary();
        int idOfMinSalary = 0;
        for (int i = 0; i < this.employeeList.size(); i++) {
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getSalary() < minSalary) {
                minSalary = this.employeeList.get(i).getSalary();
                idOfMinSalary = i;
            }
        }
        return this.employeeList.get(idOfMinSalary);
    }

    @Override
    public Employee maxSalary() {
        int maxSalary = this.employeeList.get(0).getSalary();
        int id = 0;
        for (int i = 0; i < this.employeeList.size(); i++) {
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getSalary() > maxSalary) {
                maxSalary = this.employeeList.get(i).getSalary();
                id = i;
            }
        }
        return this.employeeList.get(id);
    }

    @Override
    public int averageSalary() {
        int amountOfEmployee = 0;
        for (Employee employee : this.employeeList) {
            if (employee != null) {
                amountOfEmployee++;
            }
        }
        return monthSalary() / amountOfEmployee;
    }

    @Override
    public void indexSalary(int index) {
        for (Employee employee : this.employeeList) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() + employee.getSalary() / 100 * index);
            }
        }
    }

    @Override
    public Employee minSalaryInDepartment(int department) {
        int minSalaryInDepartment = 0;
        int idOfMinSalaryInDepartment = 0;
        for (int i = 0; i < this.employeeList.size(); i++) {
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getDepartment() == department && minSalaryInDepartment == 0) {
                minSalaryInDepartment = this.employeeList.get(i).getSalary();
            }
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getDepartment() == department && minSalaryInDepartment >= this.employeeList.get(i).getSalary()) {
                minSalaryInDepartment = this.employeeList.get(i).getSalary();
                idOfMinSalaryInDepartment = i;
            }
        }
        return this.employeeList.get(idOfMinSalaryInDepartment);
    }

    @Override
    public Employee maxSalaryInDepartment(int department) {
        int maxSalaryInDepartment = 0;
        int idOfMinSalaryInDepartment = 0;
        for (int i = 0; i < this.employeeList.size(); i++) {
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getDepartment() == department && maxSalaryInDepartment == 0) {
                maxSalaryInDepartment = this.employeeList.get(i).getSalary();
            }
            if (this.employeeList.get(i) != null && this.employeeList.get(i).getDepartment() == department && maxSalaryInDepartment <= this.employeeList.get(i).getSalary()) {
                maxSalaryInDepartment = this.employeeList.get(i).getSalary();
                idOfMinSalaryInDepartment = i;
            }
        }
        return this.employeeList.get(idOfMinSalaryInDepartment);
    }

    @Override
    public int monthSalaryInDepartment(int department) {
        int sum = 0;
        for (Employee employee : this.employeeList) {
            if (employee != null && employee.getDepartment() == department) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    @Override
    public int averageSalaryInDepartment(int department) {
        int amountOfEmployeeInDepartment = 0;
        for (Employee employee : this.employeeList) {
            if (employee != null && employee.getDepartment() == department) {
                amountOfEmployeeInDepartment++;
            }
        }
        return monthSalaryInDepartment(department) / amountOfEmployeeInDepartment;
    }

    @Override
    public void indexSalaryInDepartment(int department, int index) {
        for (Employee employee : this.employeeList) {
            if (employee != null && employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() + employee.getSalary() / 100 * index);
            }
        }
    }

    @Override
    public Collection<Employee> employeesInDepartment(int department) {
        List<Employee> employeesInDepartmentList = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (employee != null && employee.getDepartment() == department) {
                employeesInDepartmentList.add(employee);
            }
        }
        return employeesInDepartmentList;
    }

    @Override
    public Collection<Employee> salaryLessThan(int amount) {
        List<Employee> salaryLessThanList = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (employee != null && amount > employee.getSalary()) {
                salaryLessThanList.add(employee);
            }
        }
        return salaryLessThanList;
    }

    @Override
    public Collection<Employee> salaryMoreThan(int amount) {
        List<Employee> salaryMoreThanList = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (employee != null && amount < employee.getSalary()) {
                salaryMoreThanList.add(employee);
            }
        }
        return salaryMoreThanList;
    }
}
