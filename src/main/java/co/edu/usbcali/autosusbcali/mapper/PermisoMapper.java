package co.edu.usbcali.autosusbcali.mapper;

import co.edu.usbcali.autosusbcali.domain.Permiso;
import co.edu.usbcali.autosusbcali.dto.request.CrearModificarPermisoRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerPermisoResponse;

import java.util.List;

public class PermisoMapper {

    public static ObtenerPermisoResponse permisoAObtenerPermisoResponse(Permiso permiso) {
        return new ObtenerPermisoResponse(
                permiso.getId(),
                permiso.getModulo(),
                permiso.getAccion(),
                permiso.getDescripcion()
        );
    }

    public static List<ObtenerPermisoResponse> listaPermisosAListaObtenerPermisoResponse(List<Permiso> permisos) {
        return permisos.stream().map(PermisoMapper::permisoAObtenerPermisoResponse).toList();
    }

    public static Permiso crearModificarPermisoRequestAPermiso(CrearModificarPermisoRequest request) {
        return new Permiso(null, request.modulo(), request.accion(), request.descripcion());
    }
}