package co.edu.usbcali.autosusbcali.controller;

import co.edu.usbcali.autosusbcali.dto.request.CrearModificarPermisoRequest;
import co.edu.usbcali.autosusbcali.dto.response.ObtenerPermisoResponse;
import co.edu.usbcali.autosusbcali.service.IPermisoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
@AllArgsConstructor
public class PermisoController {

    private final IPermisoService permisoService;

    @GetMapping
    public List<ObtenerPermisoResponse> obtenerTodos() {
        return permisoService.obtenerTodos();
    }

    @GetMapping("/por-modulo/{modulo}")
    public List<ObtenerPermisoResponse> obtenerPorModulo(@PathVariable String modulo) {
        return permisoService.obtenerPorModulo(modulo);
    }

    @GetMapping("/por-id/{id}")
    public ResponseEntity<ObtenerPermisoResponse> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(permisoService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ObtenerPermisoResponse> crearPermiso(
            @RequestBody CrearModificarPermisoRequest request) throws Exception {
        return new ResponseEntity<>(permisoService.crearPermiso(request), HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ObtenerPermisoResponse> modificarPermiso(
            @PathVariable Long id,
            @RequestBody CrearModificarPermisoRequest request) throws Exception {
        return new ResponseEntity<>(permisoService.modificarPermiso(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPermiso(@PathVariable Long id) throws Exception {
        permisoService.eliminarPermiso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}