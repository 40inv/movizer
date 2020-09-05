package prfaz.movizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidFimFilter extends RuntimeException {
    public InvalidFimFilter(String message) {
        super(message);
    }
}
