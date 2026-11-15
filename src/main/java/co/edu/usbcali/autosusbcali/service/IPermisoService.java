package co.edu.usbcali.autosusbcali.service;

import co.edu.usbcali.autosusbcali.dto.request.CrearModificarPermisoRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerPermisoResponse;

import java.util.List;

public interface IPermisoService {

    List<ObtenerPermisoResponse> obtenerTodos();

    List<ObtenerPermisoResponse> obtenerPorModulo(String modulo);

    ObtenerPermisoResponse obtenerPorId(Long id);

    ObtenerPermisoResponse crearPermiso(CrearModificarPermisoRequest request) throws Exception;

    ObtenerPermisoResponse modificarPermiso(Long id, CrearModificarPermisoRequest request) throws Exception;

    void eliminarPermiso(Long id) throws Exception;
}