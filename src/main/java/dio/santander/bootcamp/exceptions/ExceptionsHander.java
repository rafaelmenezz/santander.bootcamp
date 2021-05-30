package dio.santander.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHander extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(BusinessExceptions.class)
    protected ResponseEntity<ExceptionsResponse> handleSecurity(BusinessExceptions e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ExceptionsResponse(e.getMessage()));

    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionsResponse> handleSecurity(NotFoundException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ExceptionsResponse(e.getMessage()));

    }
}
