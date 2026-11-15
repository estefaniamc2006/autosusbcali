package co.edu.usbcali.autosusbcali.exception;

public class RolNoEncontradoException extends RuntimeException {

    public RolNoEncontradoException(Long id) {
        super("No existe un rol con id " + id);
    }

    public RolNoEncontradoException(String nombre) {
        super("No existe un rol con nombre " + nombre);
    }
}