package husj.referencia.controller;

import husj.referencia.model.dto.response.TrasladoCompletoResponseDTO;
import husj.referencia.service.TrasladoCompletoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traslados-completos")
@RequiredArgsConstructor
public class TrasladoCompletoController {

    private final TrasladoCompletoService trasladoCompletoService;

    @Operation(summary = "Listar traslados completos",
            description = "Obtiene la lista completa de todos los traslados completos registrados en el sistema.",
            tags={"Traslados Información Completa"})
    @GetMapping
    public ResponseEntity<List<TrasladoCompletoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(trasladoCompletoService.listarTodos());
    }
}