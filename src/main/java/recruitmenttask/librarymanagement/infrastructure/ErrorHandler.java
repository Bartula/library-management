package recruitmenttask.librarymanagement.infrastructure;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import recruitmenttask.librarymanagement.api.InvalidRequestException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public String handleInvalidRequestException(InvalidRequestException exception) {
        return exception.getMessage();
    }
}
