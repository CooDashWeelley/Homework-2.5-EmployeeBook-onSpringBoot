package cdw.CooDashWeelley.EmployeeBook.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException (String message) {
        super (message);
    }
}
