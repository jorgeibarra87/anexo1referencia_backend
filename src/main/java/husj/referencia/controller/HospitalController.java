package husj.referencia.controller;

import husj.referencia.model.dto.request.HospitalReqDTO;
import husj.referencia.model.dto.response.HospitalResDTO;
import husj.referencia.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/hospitales")
@Tag(name = "Hospitales", description = "Operaciones para realizar consultas de información de hospitales en referencia")
public class HospitalController {

    private final HospitalService hospitalService;

    // Listar con paginación manual
    @Operation(summary = "Obtener Datos paginados", description = "Devuelve una lista de datos de los Hospitales de referencia paginados en los parámetros se pasa la pagina y el tamaño) .",
            tags={"Hospitales"})
    @GetMapping
    public List<HospitalResDTO> listar() {
        return hospitalService.ListarHospital();
    }

    // Buscar por ID
    @Operation(summary = "Obtener Datos por id", description = "Devuelve el Hospital por el parámetro id.", tags={"Hospitales"})
    @GetMapping("/{id}")
    public Optional<HospitalResDTO> obtenerPorId(@PathVariable Long id) {
        return hospitalService.buscarPorId(id);
    }

    // Crear nuevo
    @Operation(summary = "Crear Hospital", description = "Permite crear un nuevo Hospital.", tags={"Hospitales"})
    @PostMapping
    public HospitalReqDTO crear(@RequestBody HospitalReqDTO hospital) {
        return hospitalService.guardar(hospital);
    }

}
