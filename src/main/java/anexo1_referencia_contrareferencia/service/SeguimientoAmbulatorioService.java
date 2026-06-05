package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.SeguimientoAmbulatorioRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.SeguimientoAmbulatorioResponseDTO;

import java.util.List;

public interface SeguimientoAmbulatorioService {
    SeguimientoAmbulatorioResponseDTO crear(SeguimientoAmbulatorioRequestDTO request);
    SeguimientoAmbulatorioResponseDTO obtenerPorId(Long id);
    List<SeguimientoAmbulatorioResponseDTO> listarPorTramiteId(Long tramiteId);
    SeguimientoAmbulatorioResponseDTO actualizar(Long id, SeguimientoAmbulatorioRequestDTO request);
    void eliminar(Long id);
}
