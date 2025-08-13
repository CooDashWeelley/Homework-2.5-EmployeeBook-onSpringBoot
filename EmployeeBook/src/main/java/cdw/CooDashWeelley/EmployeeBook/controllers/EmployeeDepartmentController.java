package cdw.CooDashWeelley.EmployeeBook.controllers;

import cdw.CooDashWeelley.EmployeeBook.Employee;
import cdw.CooDashWeelley.EmployeeBook.services.EmployeeDepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @GetMapping(path = "/minSalaryInDep")
    public Employee minSalaryInDep(@RequestParam(name = "dep", required = false) int dep) {
        return service.minSalaryInDepartment(dep);
    }

    @GetMapping(path = "/maxSalaryInDep")
    public Employee maxSalaryInDep(@RequestParam(name = "dep", required = false) int dep) {
        return service.maxSalaryInDepartment(dep);
    }

    @GetMapping(path = "/monthSalaryInDep")
    public int monthSalaryInDep(@RequestParam(name = "dep", required = false) int dep) {
        return service.monthSalaryInDepartment(dep);
    }

    @GetMapping(path = "/indexSalaryInDep")
    public String indexSalaryInDep(@RequestParam(name = "dep", required = false) int dep,
                                   @RequestParam(name = "index", required = false) int index) {
        service.indexSalaryInDepartment(dep, index);
        return "ЗП проиндексирована в отделе: " + dep + " на " + index + " %";
    }

    @GetMapping(path = "/empInDep")
    public Collection<Employee> employeesInDepartment(@RequestParam(name = "dep", required = false) int dep) {
        return service.employeesInDepartment(dep);
    }

}
