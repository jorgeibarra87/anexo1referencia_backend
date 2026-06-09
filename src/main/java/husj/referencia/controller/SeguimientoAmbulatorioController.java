package husj.referencia.controller;

import husj.referencia.model.dto.request.SeguimientoAmbulatorioRequestDTO;
import husj.referencia.model.dto.response.SeguimientoAmbulatorioResponseDTO;
import husj.referencia.service.SeguimientoAmbulatorioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguimientos-ambulatorios")
@RequiredArgsConstructor
public class SeguimientoAmbulatorioController {

    private final SeguimientoAmbulatorioService service;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @Operation(summary = "Crear seguimiento ambulatorio", tags = {"Seguimientos Ambulatorios"})
    @PostMapping
    public ResponseEntity<SeguimientoAmbulatorioResponseDTO> crear(@Valid @RequestBody SeguimientoAmbulatorioRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @Operation(summary = "Obtener seguimiento ambulatorio por ID", tags = {"Seguimientos Ambulatorios"})
    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoAmbulatorioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @Operation(summary = "Listar seguimientos ambulatorios por trámite", tags = {"Seguimientos Ambulatorios"})
    @GetMapping("/tramite/{tramiteId}")
    public ResponseEntity<List<SeguimientoAmbulatorioResponseDTO>> listarPorTramite(@PathVariable Long tramiteId) {
        return ResponseEntity.ok(service.listarPorTramiteId(tramiteId));
    }

    @Operation(summary = "Actualizar seguimiento ambulatorio", tags = {"Seguimientos Ambulatorios"})
    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoAmbulatorioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SeguimientoAmbulatorioRequestDTO request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @Operation(summary = "Eliminar seguimiento ambulatorio", tags = {"Seguimientos Ambulatorios"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
