package husj.referencia.service;

import husj.referencia.model.dto.request.SeguimientoIntrahospitalarioRequestDTO;
import husj.referencia.model.dto.response.SeguimientoIntrahospitalarioResponseDTO;

import java.util.List;

public interface SeguimientoIntrahospitalarioService {
    SeguimientoIntrahospitalarioResponseDTO crear(SeguimientoIntrahospitalarioRequestDTO request);
    SeguimientoIntrahospitalarioResponseDTO obtenerPorId(Long id);
    List<SeguimientoIntrahospitalarioResponseDTO> listarPorTramiteId(Long tramiteId);
    SeguimientoIntrahospitalarioResponseDTO actualizar(Long id, SeguimientoIntrahospitalarioRequestDTO request);
    void eliminar(Long id);
}
