package husj.referencia.controller;

import husj.referencia.model.dto.request.CuentaMedicaRequestDTO;
import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;
import husj.referencia.service.CuentaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas-medicas")
@RequiredArgsConstructor
public class CuentaMedicaController {

    private final CuentaMedicaService cuentaMedicaService;

    @Operation(summary = "Crear cuentas medicas",
            description = "Permite crear nuevos registros de cuentas medicas.",
            tags={"Cuentas Médicas"})
    @PostMapping
    public ResponseEntity<CuentaMedicaResponseDTO> crear(@Valid @RequestBody CuentaMedicaRequestDTO request) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Obtener cuenta medica por ID",
            description = "Recupera la información completa de una cuenta medica específica por su identificador único.",
            tags={"Cuentas Médicas"})
    @GetMapping("/{id}")
    public ResponseEntity<CuentaMedicaResponseDTO> obtenerPorId(@PathVariable Long id) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Listar todas las cuentas medicas",
            description = "Obtiene la lista completa de todas las cuentas medicas registradas en el sistema.",
            tags={"Cuentas Médicas"})
    @GetMapping
    public ResponseEntity<List<CuentaMedicaResponseDTO>> listarTodos() {
        List<CuentaMedicaResponseDTO> lista = cuentaMedicaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Actualizar cuenta medica",
            description = "Actualiza completamente la información de una cuenta medica existente por su ID.",
            tags={"Cuentas Médicas"})
    @PutMapping("/{id}")
    public ResponseEntity<CuentaMedicaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CuentaMedicaRequestDTO request
    ) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Cambiar estado de cuenta medica",
            description = "Modifica únicamente el estado de una cuenta medica específica mediante su ID y el nuevo estado.",
            tags={"Cuentas Médicas"})
    @PatchMapping("/{id}/estado")
    public ResponseEntity<CuentaMedicaResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(cuentaMedicaService.cambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar cuenta medica",
            description = "Elimina permanentemente una cuenta medica del sistema por su identificador único.",
            tags={"Cuentas Médicas"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
