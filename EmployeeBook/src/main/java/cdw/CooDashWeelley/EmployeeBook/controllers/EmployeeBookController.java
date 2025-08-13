package cdw.CooDashWeelley.EmployeeBook.controllers;

import cdw.CooDashWeelley.EmployeeBook.Employee;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeAlreadyAddedException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeNotFoundException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.EmployeeStorageIsFullException;
import cdw.CooDashWeelley.EmployeeBook.exceptions.NotEnterDataException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cdw.CooDashWeelley.EmployeeBook.services.EmployeeServiceImpl;

import java.util.Collection;

@RequestMapping("/employee")
@RestController
public class EmployeeBookController {
    public final EmployeeServiceImpl service;

    public EmployeeBookController(EmployeeServiceImpl employeeService) {
        this.service = employeeService;
    }

    @GetMapping
    public String welcome() {
        return "welcome";
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(name = "firstName", required = false) String firstName,
                                @RequestParam(name = "lastName", required = false) String lastName,
                                @RequestParam(name = "salary", required = false) int salary,
                                @RequestParam(name = "department", required = false) int department
    ) {
        try {
            return service.add(firstName, lastName, salary, department);
        } catch (EmployeeStorageIsFullException e) {
            throw new EmployeeStorageIsFullException("storage is full");
        } catch (EmployeeAlreadyAddedException e) {
            throw new EmployeeAlreadyAddedException("already added");
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(name = "firstName", required = false) String firstName,
                                   @RequestParam(name = "lastName", required = false) String lastName,
                                   @RequestParam(name = "salary", required = false) int salary,
                                   @RequestParam(name = "department", required = false) int department
    ) {
        try {
            return service.remove(firstName, lastName, salary, department);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("not found");
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(name = "firstName", required = false) String firstName,
                                 @RequestParam(name = "lastName", required = false) String lastName,
                                 @RequestParam(name = "salary", required = false) int salary,
                                 @RequestParam(name = "department", required = false) int department
    ) {
        try {
            return service.find(firstName, lastName, salary, department);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("not found");
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/showAll")
    public Collection<Employee> showAllEmployees() {
        return service.showAll();
    }

    @GetMapping(path = "/monthSalary")
    public int monthSalary() {
        return service.monthSalary();
    }

    @GetMapping(path = "/minSalary")
    public Employee minSalary() {
        return service.minSalary();
    }

    @GetMapping(path = "/maxSalary")
    public Employee maxSalary() {
        return service.maxSalary();
    }

    @GetMapping(path = "/averageSalary")
    public int averageSalary() {
        return service.averageSalary();
    }

    @GetMapping(path = "/indexSalary")
    public String indexSalary(@RequestParam(name = "index", required = false) int index) {
        service.indexSalary(index);
        return "ЗП проиндексирована на " + index + " %";
    }


    @GetMapping(path = "/salaryLessThan")
    public Collection<Employee> salaryLessThan(@RequestParam(name = "amount", required = false) int amount) {
        return service.salaryLessThan(amount);
    }

    @GetMapping(path = "/salaryMoreThan")
    public Collection<Employee> salaryMoreThan(@RequestParam(name = "amount", required = false) int amount) {
        return service.salaryMoreThan(amount);
    }
}
