package mx.food.marketapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Peticion mala.");
    }

    public BadRequestException(String mensaje) {
        super(mensaje);
    }
    
}
