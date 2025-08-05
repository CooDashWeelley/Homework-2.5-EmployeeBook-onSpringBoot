package cdw.CooDashWeelley.EmployeeBook;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import exceptions.NotEnterDataException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class EmployeeBookController {
    public final EmployeeService employeeService;

    public EmployeeBookController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
            return employeeService.addEmployee(firstName, lastName);
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
            return employeeService.removeEmployee(firstName, lastName);
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
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("not found");
        } catch (NotEnterDataException e) {
            throw new NotEnterDataException("enter data");
        }
    }
    @GetMapping (path = "/employees")
    public Employee showAllEmployees() {
        return employeeService.showAllEmployees();
    }
}
