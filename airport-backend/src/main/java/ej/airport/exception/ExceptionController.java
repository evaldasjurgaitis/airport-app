package ej.airport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoEntryFoundException.class)
    public ResponseEntity<ExceptionResponse> invalidEntry(NoEntryFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

}
