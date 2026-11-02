package co.edu.usbcali.autosusbcali.service;

import co.edu.usbcali.autosusbcali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerRolResponse;

import java.util.List;

public interface IRolService {

    List<ObtenerRolResponse> obtenerTodos();

    List<ObtenerRolResponse> buscarRolPorEstado(Boolean activo);

    ObtenerRolResponse obtenerRolPorNombre(String nombre);

    ObtenerRolResponse obtenerRolPorId(Long id);

    ObtenerRolResponse crearRol(CrearModificarRolRequest request) throws Exception;

    ObtenerRolResponse modificarRol(Long id, CrearModificarRolRequest request) throws Exception;

    void eliminarRol(Long id) throws Exception;
}