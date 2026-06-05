package husj.referencia.controller;

import husj.referencia.model.dto.request.FacturacionRequestDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;
import husj.referencia.service.FacturacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturaciones")
@RequiredArgsConstructor
public class FacturacionController {

    private final FacturacionService facturacionService;

    @Operation(summary = "Crear facturación",
            description = "Permite crear nuevos registros de facturación.",
            tags={"Facturación"})
    @PostMapping
    public ResponseEntity<FacturacionResponseDTO> crear(@Valid @RequestBody FacturacionRequestDTO request) {
        FacturacionResponseDTO respuesta = facturacionService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Obtener facturación por ID",
            description = "Recupera la información completa de una facturación específica por su identificador único.",
            tags={"Facturación"})
    @GetMapping("/{id}")
    public ResponseEntity<FacturacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        FacturacionResponseDTO respuesta = facturacionService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Listar todas las facturaciones",
            description = "Obtiene la lista completa de todas las facturaciones registradas en el sistema.",
            tags={"Facturación"})
    @GetMapping
    public ResponseEntity<List<FacturacionResponseDTO>> listarTodos() {
        List<FacturacionResponseDTO> lista = facturacionService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Actualizar facturación",
            description = "Actualiza completamente la información de una facturación existente por su ID.",
            tags={"Facturación"})
    @PutMapping("/{id}")
    public ResponseEntity<FacturacionResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FacturacionRequestDTO request
    ) {
        FacturacionResponseDTO respuesta = facturacionService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Cambiar estado de facturación",
            description = "Modifica únicamente el estado de una facturación específica mediante su ID y el nuevo estado.",
            tags={"Facturación"})
    @PatchMapping("/{id}/estado")
    public ResponseEntity<FacturacionResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(facturacionService.cambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar facturación",
            description = "Elimina permanentemente una facturación del sistema por su identificador único.",
            tags={"Facturación"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}