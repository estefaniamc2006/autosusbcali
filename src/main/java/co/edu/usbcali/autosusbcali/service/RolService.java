package co.edu.usbcali.autosusbcali.service;

import co.edu.usbcali.autosusbcali.domain.Rol;
import co.edu.usbcali.autosusbcali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosusbcali.exception.RolNoEncontradoException;
import co.edu.usbcali.autosusbcali.mapper.RolMapper;
import co.edu.usbcali.autosusbcali.repository.RolRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import co.edu.usbcali.autosusbcali.exception.ReglaNegocioException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RolService implements IRolService {

    private final RolRepository rolRepository;

    @Override
    public List<ObtenerRolResponse> obtenerTodos() {
        log.debug("Consultando todos los roles");
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findAll());
    }

    @Override
    public List<ObtenerRolResponse> buscarRolPorEstado(Boolean activo) {
        log.debug("Consultando roles con activo={}", activo);
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findByActivo(activo));
    }

    @Override
    public ObtenerRolResponse obtenerRolPorNombre(String nombre) {
        Rol rol = rolRepository.findByNombre(nombre)
                .orElseThrow(() -> {
                    log.warn("No se encontró el rol con nombre {}", nombre);
                    return new RolNoEncontradoException(nombre);
                });
        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public ObtenerRolResponse obtenerRolPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró el rol con id {}", id);
                    return new RolNoEncontradoException(id);
                });
        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public ObtenerRolResponse crearRol(CrearModificarRolRequest request) throws Exception {
        if (request == null) {
            throw new ReglaNegocioException("El objeto CrearModificarRolRequest no puede ser nulo.");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new ReglaNegocioException("El nombre no puede ser nulo ni estar vacío.");
        }
        if (request.descripcion() == null || request.descripcion().isBlank()) {
            throw new ReglaNegocioException("La descripción no puede ser nula ni estar vacía.");
        }

        log.info("Creando rol con nombre {}", request.nombre());

        if (rolRepository.existsByNombre(request.nombre())) {
            log.warn("Intento de crear un rol duplicado con nombre {}", request.nombre());
            throw new ReglaNegocioException("Ya existe el rol con el mismo nombre.");
        }

        try {
            Rol rol = rolRepository.save(RolMapper.crearModificarRolRequestARol(request));
            log.info("Rol {} creado con id {}", rol.getNombre(), rol.getId());
            return RolMapper.rolAObtenerRolResponse(rol);
        } catch (DataAccessException e) {
            // El stack trace va como último parámetro
            log.error("Error de base de datos al crear el rol {}", request.nombre(), e);
            throw e;
        }
    }

    @Override
    public ObtenerRolResponse modificarRol(Long id, CrearModificarRolRequest request) throws Exception {
        if (id == null) {
            throw new ReglaNegocioException("El id no puede ser nulo.");
        }
        if (request == null) {
            throw new ReglaNegocioException("El objeto CrearModificarRolRequest no puede ser nulo.");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new ReglaNegocioException("El nombre no puede ser nulo ni estar vacío.");
        }
        if (request.descripcion() == null || request.descripcion().isBlank()) {
            throw new ReglaNegocioException("La descripción no puede ser nula ni estar vacía.");
        }

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de modificar un rol inexistente con id {}", id);
                    return new RolNoEncontradoException(id);
                });

        if (!rol.getNombre().equals(request.nombre())
                && rolRepository.existsByNombre(request.nombre())) {
            log.warn("Modificación del rol {} rechazada: el nombre {} ya existe", id, request.nombre());
            throw new ReglaNegocioException("Ya existe otro rol con el mismo nombre.");
        }

        try {
            rol.setNombre(request.nombre());
            rol.setDescripcion(request.descripcion());
            rol = rolRepository.save(rol);
            log.info("Rol con id {} modificado; nuevo nombre {}", id, rol.getNombre());
            return RolMapper.rolAObtenerRolResponse(rol);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al modificar el rol con id {}", id, e);
            throw e;
        }
    }

    @Override
    public void eliminarRol(Long id) throws Exception {
        if (id == null) {
            throw new ReglaNegocioException("El id no puede ser nulo.");
        }

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de eliminar un rol inexistente con id {}", id);
                    return new RolNoEncontradoException(id);
                });

        log.info("Solicitud de eliminación del rol {} (id {})", rol.getNombre(), id);

        try {
            rolRepository.delete(rol);
            log.info("AUDITORÍA: rol {} (id {}) eliminado físicamente", rol.getNombre(), id);
        } catch (DataIntegrityViolationException e) {
            log.error("No se puede eliminar el rol {} (id {}) porque está en uso", rol.getNombre(), id, e);
            throw new ReglaNegocioException("No se puede eliminar el rol porque está asignado a otros registros.");
        }
    }
}