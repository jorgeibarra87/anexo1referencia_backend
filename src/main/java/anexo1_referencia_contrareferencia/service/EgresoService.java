package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.EgresoRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.EgresoResponseDTO;

public interface EgresoService {
    EgresoResponseDTO crear(EgresoRequestDTO request);
    EgresoResponseDTO obtenerPorId(Long id);
    java.util.Optional<EgresoResponseDTO> obtenerPorTramiteId(Long tramiteId);
    EgresoResponseDTO actualizar(Long id, EgresoRequestDTO request);
    void eliminar(Long id);
}
