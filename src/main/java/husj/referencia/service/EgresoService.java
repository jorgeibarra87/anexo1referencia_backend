package husj.referencia.service;

import husj.referencia.model.dto.request.EgresoRequestDTO;
import husj.referencia.model.dto.response.EgresoResponseDTO;

public interface EgresoService {
    EgresoResponseDTO crear(EgresoRequestDTO request);
    EgresoResponseDTO obtenerPorId(Long id);
    java.util.Optional<EgresoResponseDTO> obtenerPorTramiteId(Long tramiteId);
    EgresoResponseDTO actualizar(Long id, EgresoRequestDTO request);
    void eliminar(Long id);
}
