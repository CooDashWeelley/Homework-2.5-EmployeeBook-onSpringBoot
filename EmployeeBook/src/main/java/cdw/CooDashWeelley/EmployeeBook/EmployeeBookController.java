package cdw.CooDashWeelley.EmployeeBook;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import exceptions.NotEnterDataException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                                @RequestParam(name = "lastName", required = false) String lastName
    ) {
        try {
            return service.add(firstName, lastName);
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
                                   @RequestParam(name = "lastName", required = false) String lastName
    ) {
        try {
            return service.remove(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("not found");
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(name = "firstName", required = false) String firstName,
                                 @RequestParam(name = "lastName", required = false) String lastName
    ) {
        try {
            return service.find(firstName, lastName);
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
}
