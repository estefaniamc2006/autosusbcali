package co.edu.usbcali.autosusbcali.service;

import co.edu.usbcali.autosusbcali.domain.Permiso;
import co.edu.usbcali.autosusbcali.dto.request.CrearModificarPermisoRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerPermisoResponse;
import co.edu.usbcali.autosusbcali.exception.PermisoNoEncontradoException;
import co.edu.usbcali.autosusbcali.mapper.PermisoMapper;
import co.edu.usbcali.autosusbcali.repository.PermisoRepository;
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
public class PermisoService implements IPermisoService {

    private final PermisoRepository permisoRepository;

    @Override
    public List<ObtenerPermisoResponse> obtenerTodos() {
        log.debug("Consultando todos los permisos");
        return PermisoMapper.listaPermisosAListaObtenerPermisoResponse(permisoRepository.findAll());
    }

    @Override
    public List<ObtenerPermisoResponse> obtenerPorModulo(String modulo) {
        log.debug("Consultando permisos del módulo {}", modulo);
        return PermisoMapper.listaPermisosAListaObtenerPermisoResponse(permisoRepository.findByModulo(modulo));
    }

    @Override
    public ObtenerPermisoResponse obtenerPorId(Long id) {
        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró el permiso con id {}", id);
                    return new PermisoNoEncontradoException(id);
                });
        return PermisoMapper.permisoAObtenerPermisoResponse(permiso);
    }

    @Override
    public ObtenerPermisoResponse crearPermiso(CrearModificarPermisoRequest request) throws Exception {
        if (request == null) {
            throw new ReglaNegocioException("El objeto CrearModificarPermisoRequest no puede ser nulo.");
        }
        if (request.modulo() == null || request.modulo().isBlank()) {
            throw new ReglaNegocioException("El módulo no puede ser nulo ni estar vacío.");
        }
        if (request.accion() == null || request.accion().isBlank()) {
            throw new ReglaNegocioException("La acción no puede ser nula ni estar vacía.");
        }

        log.info("Creando permiso {} para el módulo {}", request.accion(), request.modulo());

        if (permisoRepository.existsByModuloAndAccion(request.modulo(), request.accion())) {
            log.warn("Intento de crear un permiso duplicado: {} en {}", request.accion(), request.modulo());
            throw new ReglaNegocioException("Ya existe un permiso con esa acción en el módulo.");
        }

        try {
            Permiso permiso = permisoRepository.save(PermisoMapper.crearModificarPermisoRequestAPermiso(request));
            log.info("Permiso creado con id {}", permiso.getId());
            return PermisoMapper.permisoAObtenerPermisoResponse(permiso);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al crear el permiso {} en {}", request.accion(), request.modulo(), e);
            throw e;
        }
    }

    @Override
    public ObtenerPermisoResponse modificarPermiso(Long id, CrearModificarPermisoRequest request) throws Exception {
        if (id == null) {
            throw new ReglaNegocioException("El id no puede ser nulo.");
        }
        if (request == null) {
            throw new ReglaNegocioException("El objeto CrearModificarPermisoRequest no puede ser nulo.");
        }
        if (request.modulo() == null || request.modulo().isBlank()) {
            throw new ReglaNegocioException("El módulo no puede ser nulo ni estar vacío.");
        }
        if (request.accion() == null || request.accion().isBlank()) {
            throw new ReglaNegocioException("La acción no puede ser nula ni estar vacía.");
        }

        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de modificar un permiso inexistente con id {}", id);
                    return new PermisoNoEncontradoException(id);
                });

        // Solo revisar duplicados si cambia la combinación módulo + acción
        boolean cambiaClave = !permiso.getModulo().equals(request.modulo())
                || !permiso.getAccion().equals(request.accion());

        if (cambiaClave && permisoRepository.existsByModuloAndAccion(request.modulo(), request.accion())) {
            log.warn("Modificación del permiso {} rechazada: ya existe {} en {}", id, request.accion(), request.modulo());
            throw new ReglaNegocioException("Ya existe otro permiso con esa acción en el módulo.");
        }

        try {
            permiso.setModulo(request.modulo());
            permiso.setAccion(request.accion());
            permiso.setDescripcion(request.descripcion());
            permiso = permisoRepository.save(permiso);
            log.info("Permiso con id {} modificado a {} en {}", id, permiso.getAccion(), permiso.getModulo());
            return PermisoMapper.permisoAObtenerPermisoResponse(permiso);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al modificar el permiso con id {}", id, e);
            throw e;
        }
    }

    @Override
    public void eliminarPermiso(Long id) throws Exception {
        if (id == null) {
            throw new ReglaNegocioException("El id no puede ser nulo.");
        }

        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de eliminar un permiso inexistente con id {}", id);
                    return new PermisoNoEncontradoException(id);
                });

        log.info("Solicitud de eliminación del permiso {} en {} (id {})", permiso.getAccion(), permiso.getModulo(), id);

        try {
            permisoRepository.delete(permiso);
            log.info("AUDITORÍA: permiso {} en {} (id {}) eliminado físicamente", permiso.getAccion(), permiso.getModulo(), id);
        } catch (DataIntegrityViolationException e) {
            log.error("No se puede eliminar el permiso con id {} porque está asignado a roles", id, e);
            throw new ReglaNegocioException("No se puede eliminar el permiso porque está asignado a uno o más roles.");
        }
    }
}