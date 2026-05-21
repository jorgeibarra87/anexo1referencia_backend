package anexo1_referencia_contrareferencia.controller;

import anexo1_referencia_contrareferencia.model.dto.request.EgresoRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.EgresoResponseDTO;
import anexo1_referencia_contrareferencia.service.EgresoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/egresos")
@RequiredArgsConstructor
public class EgresoController {

    private final EgresoService egresoService;

    @Operation(summary = "Crear egreso", tags = {"Egresos"})
    @PostMapping
    public ResponseEntity<EgresoResponseDTO> crear(@Valid @RequestBody EgresoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(egresoService.crear(request));
    }

    @Operation(summary = "Obtener egreso por ID", tags = {"Egresos"})
    @GetMapping("/{id}")
    public ResponseEntity<EgresoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(egresoService.obtenerPorId(id));
    }

    @Operation(summary = "Obtener egreso por ID de trámite", tags = {"Egresos"})
    @GetMapping("/tramite/{tramiteId}")
    public ResponseEntity<EgresoResponseDTO> obtenerPorTramiteId(@PathVariable Long tramiteId) {
        return ResponseEntity.ok(egresoService.obtenerPorTramiteId(tramiteId));
    }

    @Operation(summary = "Actualizar egreso", tags = {"Egresos"})
    @PutMapping("/{id}")
    public ResponseEntity<EgresoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EgresoRequestDTO request) {
        return ResponseEntity.ok(egresoService.actualizar(id, request));
    }

    @Operation(summary = "Eliminar egreso", tags = {"Egresos"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        egresoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
