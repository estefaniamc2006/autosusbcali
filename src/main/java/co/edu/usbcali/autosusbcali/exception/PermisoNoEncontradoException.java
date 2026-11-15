package co.edu.usbcali.autosusbcali.exception;

public class PermisoNoEncontradoException extends RuntimeException {

    public PermisoNoEncontradoException(Long id) {
        super("No existe un permiso con id " + id);
    }
}