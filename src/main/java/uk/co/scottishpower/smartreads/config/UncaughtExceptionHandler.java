package uk.co.scottishpower.smartreads.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.co.scottishpower.smartreads.exceptions.BusinessLogicException;

@ControllerAdvice
public class UncaughtExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(UncaughtExceptionHandler.class);

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<Object> handleCustomException(Throwable ex, HandlerMethod handlerMethod){

        logger.error("Exception thrown: {}", ex);

        if(ex instanceof BusinessLogicException){

            String message = String.format("{message: %s}", ex.getMessage());

            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(message);
        }else{

            String message = "{message: Something unexpected happened please try again later or contact support}";

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body(message);
        }
    }
}
