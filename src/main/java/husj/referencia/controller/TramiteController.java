package husj.referencia.controller;

import husj.referencia.model.dto.request.TramiteRequestDTO;
import husj.referencia.model.dto.response.TramiteCompletoResponseDTO;
import husj.referencia.model.dto.response.TramiteResponseDTO;
import husj.referencia.service.TramiteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tramites")
@RequiredArgsConstructor
public class TramiteController {

    private final TramiteService tramiteService;

    @Operation(summary = "Crear trámite", tags = {"Trámites"})
    @PostMapping
    public ResponseEntity<TramiteResponseDTO> crear(@Valid @RequestBody TramiteRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tramiteService.crear(request));
    }

    @Operation(summary = "Obtener trámite por ID", tags = {"Trámites"})
    @GetMapping("/{id}")
    public ResponseEntity<TramiteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tramiteService.obtenerPorId(id));
    }

    @Operation(summary = "Listar todos los trámites", tags = {"Trámites"})
    @GetMapping
    public ResponseEntity<List<TramiteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tramiteService.listarTodos());
    }

    @Operation(summary = "Listar trámites con seguimientos, egreso y ambulatorio", tags = {"Trámites"})
    @GetMapping("/completos")
    public ResponseEntity<List<TramiteCompletoResponseDTO>> listarCompletos() {
        return ResponseEntity.ok(tramiteService.listarCompletos());
    }

    @Operation(summary = "Actualizar trámite", tags = {"Trámites"})
    @PutMapping("/{id}")
    public ResponseEntity<TramiteResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TramiteRequestDTO request) {
        return ResponseEntity.ok(tramiteService.actualizar(id, request));
    }

    @Operation(summary = "Cambiar estado de trámite", tags = {"Trámites"})
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TramiteResponseDTO> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(tramiteService.cambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar trámite", tags = {"Trámites"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tramiteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
