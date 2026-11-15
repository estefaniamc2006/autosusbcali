package co.edu.usbcali.autosusbcali.dto.request;

public record CrearModificarPermisoRequest(
        String modulo,
        String accion,
        String descripcion
) {
}