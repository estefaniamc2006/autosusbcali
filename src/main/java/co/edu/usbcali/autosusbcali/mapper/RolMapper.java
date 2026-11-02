package co.edu.usbcali.autosusbcali.mapper;

import co.edu.usbcali.autosusbcali.domain.Rol;
import co.edu.usbcali.autosusbcali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerRolResponse;

import java.util.List;

public class RolMapper {

    public static ObtenerRolResponse rolAObtenerRolResponse(Rol rol) {
        return new ObtenerRolResponse(rol.getId(), rol.getNombre());
    }

    public static List<ObtenerRolResponse> listaRolesAListaObtenerRolResponse(List<Rol> roles) {
        return roles.stream().map(RolMapper::rolAObtenerRolResponse).toList();
    }

    public static Rol crearModificarRolRequestARol(CrearModificarRolRequest request) {
        return new Rol(
                null,
                request.nombre(),
                request.descripcion(),
                true
        );
    }
}