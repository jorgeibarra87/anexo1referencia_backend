package husj.referencia.controller;

import husj.referencia.model.dto.request.IngresoReqDTO;
import husj.referencia.service.IngresoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/ingresos")
@Tag(name = "Ingresos", description = "Operaciones para realizar consultas de información de Ingresos en referencia")
public class IngresoController {
    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    // Listar con paginación manual
    @Operation(summary = "Obtener Ingresos paginados", description = "Devuelve una lista de datos de los Ingresos de referencia paginados en los parámetros se pasa la pagina y el tamaño) .",
            tags={"Ingresos"})
    @GetMapping
    public Page<IngresoReqDTO> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ingresoService.ListarIngreso(page, size);
    }

    // Buscar por ID
    @Operation(summary = "Obtener Ingreso por id", description = "Devuelve el Ingreso por el parámetro id.", tags={"Ingresos"})
    @GetMapping("/{id}")
    public Optional<IngresoReqDTO> obtenerPorId(@PathVariable Long id) {
        return ingresoService.buscarPorId(id);
    }

    // Crear nuevo
    @Operation(summary = "Crear Ingreso", description = "Permite crear un nuevo Ingreso.", tags={"Ingresos"})
    @PostMapping
    public IngresoReqDTO crear(@RequestBody IngresoReqDTO ingreso) {
        return ingresoService.guardar(ingreso);
    }


}
