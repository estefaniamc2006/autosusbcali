
package co.edu.usbcali.autosusbcali.dto.response;

public record ObtenerPermisoResponse(
        Long id,
        String modulo,
        String accion,
        String descripcion
) {
}