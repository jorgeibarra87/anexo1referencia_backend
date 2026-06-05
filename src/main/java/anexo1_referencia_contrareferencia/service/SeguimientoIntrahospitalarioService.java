package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.SeguimientoIntrahospitalarioRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.SeguimientoIntrahospitalarioResponseDTO;

import java.util.List;

public interface SeguimientoIntrahospitalarioService {
    SeguimientoIntrahospitalarioResponseDTO crear(SeguimientoIntrahospitalarioRequestDTO request);
    SeguimientoIntrahospitalarioResponseDTO obtenerPorId(Long id);
    List<SeguimientoIntrahospitalarioResponseDTO> listarPorTramiteId(Long tramiteId);
    SeguimientoIntrahospitalarioResponseDTO actualizar(Long id, SeguimientoIntrahospitalarioRequestDTO request);
    void eliminar(Long id);
}
