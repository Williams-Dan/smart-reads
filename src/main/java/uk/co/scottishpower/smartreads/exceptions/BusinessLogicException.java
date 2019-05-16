package uk.co.scottishpower.smartreads.exceptions;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String message){
        super(message);
    }
}
