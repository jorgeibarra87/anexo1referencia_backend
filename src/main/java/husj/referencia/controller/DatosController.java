package husj.referencia.controller;

import husj.referencia.model.dto.request.DatosIngresoUpdateReqDTO;
import husj.referencia.model.dto.request.DatosReqDTO;
import husj.referencia.model.dto.response.DatosResDTO;
import husj.referencia.service.DatosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Datos", description = "Operaciones para realizar consultas de información de pacientes de referencia")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@RestController
@RequestMapping("/datos")
public class DatosController {

    private final DatosService datosService;

    // Listar con paginación manual
    @Operation(summary = "Obtener Datos paginados", description = "Devuelve una lista de datos de los pacientes de referencia paginados en los parámetros se pasa la pagina y el tamaño) .", tags={"Datos"})
    @GetMapping
    public Page<DatosResDTO> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return datosService.listarDatos(page, size);
    }
    // endpoint para filtrar por rango de fechas
    @Operation(summary = "Obtener Datos filtrados",description = "Devuelve una lista de datos de los pacientes de referencia filtrados en un rango de fechas.",tags = {"Datos"}    )
    @GetMapping("/filtro-fecha")
    public ResponseEntity<List<DatosResDTO>> filtrarPorFecha(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            return ResponseEntity.badRequest().build();
        }
        List<DatosResDTO> resultado = datosService.listarDatosPorRangoFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(resultado);
    }

    @Operation(
            summary = "Buscar Datos por ID o Documento",
            description = "Devuelve una lista de datos que coinciden con el valor proporcionado, buscando primero por ID y luego por Documento si es necesario.",
            tags = {"Datos"}
    )
    @GetMapping("/buscar")
    public ResponseEntity<List<DatosResDTO>> buscarPorIdODocumento(@RequestParam("valor") String valor) {
        // La lógica del servicio se encarga de determinar el tipo de búsqueda
        List<DatosResDTO> resultado = datosService.buscarPorIdODocumento(valor);

        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Crear Datos", description = "Permite crear un nuevo dato.", tags={"Datos"})
    @PostMapping
    public DatosResDTO crear(@RequestBody DatosReqDTO datos) {
        return datosService.guardar(datos);
    }

    @Operation(summary = "Actualiza la fecha y hora de actualizacion para busqueda de ingresos posteriores", description = "Permite actualizar en lote la fecha y hora de actualizacion de los registros cuyos IDs se envian en el cuerpo de la solicitud.", tags={"Datos"})
    @PutMapping("/verificar-registros")
    public ResponseEntity<Void> verificarRegistrosEnLote(@RequestBody List<Long> ids) {
        datosService.actualizarFechasRegistroParaBuscarIngresos(ids);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Actualizar Datos", description = "Permite actualizar un dato existente.", tags={"Datos"})
    @PutMapping("/actualizar-ingresos")
    public ResponseEntity<List<DatosResDTO>> actualizar(@RequestBody List<DatosIngresoUpdateReqDTO> datos) {
        return ResponseEntity.ok(datosService.actualizarIngresos(datos));
    }

    @PutMapping("/observacion-triage/{id}")
    public ResponseEntity<DatosResDTO> actualizarComentarioTriage(@PathVariable Long id, @RequestParam String observacion) {
        return ResponseEntity.ok(datosService.actualizarComentarioTriage(id, observacion));
    }

}
