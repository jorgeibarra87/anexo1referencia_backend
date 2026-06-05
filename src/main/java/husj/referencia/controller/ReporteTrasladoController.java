package husj.referencia.controller;

import husj.referencia.model.dto.response.ReporteTrasladoResponseDTO;
import husj.referencia.service.ReporteTrasladoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reporte")
@RequiredArgsConstructor
public class ReporteTrasladoController {

    private final ReporteTrasladoService reporteTrasladoService;

    @Operation(
            summary = "Generar reporte",
            description = "Genera el reporte consolidado de traslados, facturación y cuentas médicas por rango de fechas"
    )
    @GetMapping("/traslados")
    public ResponseEntity<List<ReporteTrasladoResponseDTO>> generarReporte(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin
    ) {
        return ResponseEntity.ok(reporteTrasladoService.generarReporte(fechaInicio, fechaFin));
    }
}