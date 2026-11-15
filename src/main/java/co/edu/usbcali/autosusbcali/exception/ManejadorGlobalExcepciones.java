package co.edu.usbcali.autosusbcali.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler({RolNoEncontradoException.class, PermisoNoEncontradoException.class})
    public ResponseEntity<String> manejarNoEncontrado(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<String> manejarReglaNegocio(ReglaNegocioException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
    }
}