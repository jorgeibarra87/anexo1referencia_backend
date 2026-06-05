package husj.referencia.controller;

import husj.referencia.model.dto.request.TipoSolicitudCatalogoRequestDTO;
import husj.referencia.model.dto.response.TipoSolicitudCatalogoResponseDTO;
import husj.referencia.service.TipoSolicitudCatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-solicitud")
@RequiredArgsConstructor
public class TipoSolicitudCatalogoController {

    private final TipoSolicitudCatalogoService service;

    @Operation(summary = "Crear tipo de solicitud", tags = {"Tipos de Solicitud"})
    @PostMapping
    public ResponseEntity<TipoSolicitudCatalogoResponseDTO> crear(@Valid @RequestBody TipoSolicitudCatalogoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @Operation(summary = "Obtener tipo de solicitud por ID", tags = {"Tipos de Solicitud"})
    @GetMapping("/{id}")
    public ResponseEntity<TipoSolicitudCatalogoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @Operation(summary = "Listar todos los tipos de solicitud", tags = {"Tipos de Solicitud"})
    @GetMapping
    public ResponseEntity<List<TipoSolicitudCatalogoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Listar tipos de solicitud activos", tags = {"Tipos de Solicitud"})
    @GetMapping("/activos")
    public ResponseEntity<List<TipoSolicitudCatalogoResponseDTO>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @Operation(summary = "Actualizar tipo de solicitud", tags = {"Tipos de Solicitud"})
    @PutMapping("/{id}")
    public ResponseEntity<TipoSolicitudCatalogoResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TipoSolicitudCatalogoRequestDTO request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @Operation(summary = "Eliminar tipo de solicitud", tags = {"Tipos de Solicitud"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
