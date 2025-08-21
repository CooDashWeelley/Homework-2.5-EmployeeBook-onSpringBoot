package cdw.CooDashWeelley.EmployeeBook.controllers;

import cdw.CooDashWeelley.EmployeeBook.exceptions.NotEnterDataException;
import cdw.CooDashWeelley.EmployeeBook.Employee;
import cdw.CooDashWeelley.EmployeeBook.services.EmployeeDepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/employee/department")
@RestController
public class EmployeeDepartmentController {
    private final EmployeeDepartmentServiceImpl service;

    public EmployeeDepartmentController(EmployeeDepartmentServiceImpl employeeDepartmentService) {
        this.service = employeeDepartmentService;
    }

    @GetMapping
    public String welcomeInDepartment() {
        return "Welcome In Department";
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalaryInDep(@RequestParam(name = "departmentId", required = false) int dep) {
        try {
            return service.minSalaryInDepartment(dep);
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryInDep(@RequestParam(name = "departmentId", required = false) int dep) {
        try {
            return service.maxSalaryInDepartment(dep);
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/monthSalary")
    public int monthSalaryInDep(@RequestParam(name = "departmentId", required = false) int dep) {
        try {
            return service.monthSalaryInDepartment(dep);
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/indexSalary")
    public String indexSalaryInDep(@RequestParam(name = "departmentId", required = false) int dep,
                                   @RequestParam(name = "index", required = false) int index) {
        try {
            service.indexSalaryInDepartment(dep, index);
            return "ЗП проиндексирована в отделе: " + dep + " на " + index + " %";
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/all")
    public List<Employee> employeesInDepartment(@RequestParam(value = "departmentId", required = false) Integer dep) {
        if (dep == null) {
            return service.getAllEmployeesByDepartment();
        }
        return service.employeesInDepartment(dep);
    }

}
