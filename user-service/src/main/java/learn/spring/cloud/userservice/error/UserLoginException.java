package learn.spring.cloud.userservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserLoginException extends RuntimeException {
    public UserLoginException(String message){
        super(message);
    }
    @ControllerAdvice
    @ResponseBody
    public static class ExceptionHandle{
        @ExceptionHandler(UserLoginException.class)
        public ResponseEntity<String> handleException(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
