package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.TramiteRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.TramiteCompletoResponseDTO;
import anexo1_referencia_contrareferencia.model.dto.response.TramiteResponseDTO;

import java.util.List;

public interface TramiteService {
    TramiteResponseDTO crear(TramiteRequestDTO request);
    TramiteResponseDTO obtenerPorId(Long id);
    List<TramiteResponseDTO> listarTodos();
    List<TramiteCompletoResponseDTO> listarCompletos();
    TramiteResponseDTO actualizar(Long id, TramiteRequestDTO request);
    TramiteResponseDTO cambiarEstado(Long id, String estado);
    void eliminar(Long id);
}
