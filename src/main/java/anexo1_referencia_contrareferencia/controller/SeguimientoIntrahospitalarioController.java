package anexo1_referencia_contrareferencia.controller;

import anexo1_referencia_contrareferencia.model.dto.request.SeguimientoIntrahospitalarioRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.SeguimientoIntrahospitalarioResponseDTO;
import anexo1_referencia_contrareferencia.service.SeguimientoIntrahospitalarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguimientos-intra")
@RequiredArgsConstructor
public class SeguimientoIntrahospitalarioController {

    private final SeguimientoIntrahospitalarioService service;

    @Operation(summary = "Crear seguimiento intrahospitalario", tags = {"Seguimientos Intrahospitalarios"})
    @PostMapping
    public ResponseEntity<SeguimientoIntrahospitalarioResponseDTO> crear(@Valid @RequestBody SeguimientoIntrahospitalarioRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @Operation(summary = "Obtener seguimiento intrahospitalario por ID", tags = {"Seguimientos Intrahospitalarios"})
    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoIntrahospitalarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @Operation(summary = "Listar seguimientos por trámite", tags = {"Seguimientos Intrahospitalarios"})
    @GetMapping("/tramite/{tramiteId}")
    public ResponseEntity<List<SeguimientoIntrahospitalarioResponseDTO>> listarPorTramite(@PathVariable Long tramiteId) {
        return ResponseEntity.ok(service.listarPorTramiteId(tramiteId));
    }

    @Operation(summary = "Actualizar seguimiento intrahospitalario", tags = {"Seguimientos Intrahospitalarios"})
    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoIntrahospitalarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SeguimientoIntrahospitalarioRequestDTO request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @Operation(summary = "Eliminar seguimiento intrahospitalario", tags = {"Seguimientos Intrahospitalarios"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
