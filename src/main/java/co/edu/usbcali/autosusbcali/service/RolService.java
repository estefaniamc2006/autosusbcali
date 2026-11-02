package co.edu.usbcali.autosusbcali.service;

import co.edu.usbcali.autosusbcali.domain.Rol;
import co.edu.usbcali.autosusbcali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosusbcali.mapper.RolMapper;
import co.edu.usbcali.autosusbcali.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolService implements IRolService {

    private final RolRepository rolRepository;

    @Override
    public List<ObtenerRolResponse> obtenerTodos() {
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findAll());
    }

    @Override
    public List<ObtenerRolResponse> buscarRolPorEstado(Boolean activo) {
        List<Rol> roles = rolRepository.findByActivo(activo);
        return RolMapper.listaRolesAListaObtenerRolResponse(roles);
    }

    @Override
    public ObtenerRolResponse obtenerRolPorNombre(String nombre) {
        Rol rol = rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol por nombre: " + nombre));
        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public ObtenerRolResponse obtenerRolPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol por id " + id));
        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public ObtenerRolResponse crearRol(CrearModificarRolRequest request) throws Exception {
        if (request == null) {
            throw new Exception("El objeto CrearModificarRolRequest no puede ser nulo.");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new Exception("El nombre no puede ser nulo ni estar vacío.");
        }
        if (request.descripcion() == null || request.descripcion().isBlank()) {
            throw new Exception("La descripción no puede ser nula ni estar vacía.");
        }
        if (rolRepository.existsByNombre(request.nombre())) {
            throw new Exception("Ya existe el rol con el mismo nombre.");
        }

        Rol rol = RolMapper.crearModificarRolRequestARol(request);
        rol = rolRepository.save(rol);

        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public ObtenerRolResponse modificarRol(Long id, CrearModificarRolRequest request) throws Exception {
        if (id == null) {
            throw new Exception("El id no puede ser nulo.");
        }
        if (request == null) {
            throw new Exception("El objeto CrearModificarRolRequest no puede ser nulo.");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new Exception("El nombre no puede ser nulo ni estar vacío.");
        }
        if (request.descripcion() == null || request.descripcion().isBlank()) {
            throw new Exception("La descripción no puede ser nula ni estar vacía.");
        }

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe un rol con el id " + id));

        // Si cambia el nombre, verificar que no lo tenga otro rol
        if (!rol.getNombre().equals(request.nombre())
                && rolRepository.existsByNombre(request.nombre())) {
            throw new Exception("Ya existe otro rol con el mismo nombre.");
        }

        rol.setNombre(request.nombre());
        rol.setDescripcion(request.descripcion());

        rol = rolRepository.save(rol);
        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public void eliminarRol(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El id no puede ser nulo.");
        }

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe un rol con el id " + id));

        if (Boolean.FALSE.equals(rol.getActivo())) {
            throw new Exception("El rol ya se encuentra inactivo.");
        }

        // Eliminación lógica: el rol queda inactivo, no se borra de la BD
        rol.setActivo(false);
        rolRepository.save(rol);
    }
}