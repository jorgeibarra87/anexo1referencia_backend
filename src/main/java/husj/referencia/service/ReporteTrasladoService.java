package husj.referencia.service;

import husj.referencia.model.dto.response.ReporteTrasladoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteTrasladoService {
    List<ReporteTrasladoResponseDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin);
}