package husj.referencia.controller;

import husj.referencia.model.dto.request.TrasladoRequestDTO;
import husj.referencia.model.dto.response.TrasladoResponseDTO;
import husj.referencia.service.TrasladoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traslados")
@RequiredArgsConstructor
public class TrasladoController {

    private final TrasladoService trasladoService;

    @Operation(summary = "Crear traslado",
            description = "Permite crear nuevos registros de traslados.",
            tags={"Traslados"})
    @PostMapping
    public ResponseEntity<TrasladoResponseDTO> crear(@Valid @RequestBody TrasladoRequestDTO request) {
        TrasladoResponseDTO respuesta = trasladoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Obtener traslado por ID",
            description = "Recupera la información completa de un traslado específico por su identificador único.",
            tags={"Traslados"})
    @GetMapping("/{id}")
    public ResponseEntity<TrasladoResponseDTO> obtenerPorId(@PathVariable Long id) {
        TrasladoResponseDTO respuesta = trasladoService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Listar todos los traslados",
            description = "Obtiene la lista completa de todos los traslados registrados en el sistema.",
            tags={"Traslados"})
    @GetMapping
    public ResponseEntity<List<TrasladoResponseDTO>> listarTodos() {
        List<TrasladoResponseDTO> lista = trasladoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Actualizar traslado",
            description = "Actualiza completamente la información de un traslado existente por su ID.",
            tags={"Traslados"})
    @PutMapping("/{id}")
    public ResponseEntity<TrasladoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TrasladoRequestDTO request
    ) {
        TrasladoResponseDTO respuesta = trasladoService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Cambiar estado de traslado",
            description = "Modifica únicamente el estado de un traslado específico mediante su ID y el nuevo estado.",
            tags={"Traslados"})
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TrasladoResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(trasladoService.cambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar traslado",
            description = "Elimina permanentemente un traslado del sistema por su identificador único.",
            tags={"Traslados"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        trasladoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}