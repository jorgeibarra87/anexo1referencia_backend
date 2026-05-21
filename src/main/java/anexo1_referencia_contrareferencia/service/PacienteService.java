package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.PacienteRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.PacienteResponseDTO;

import java.util.List;

public interface PacienteService {
    PacienteResponseDTO crear(PacienteRequestDTO request);
    PacienteResponseDTO obtenerPorId(Long id);
    List<PacienteResponseDTO> listarTodos();
    PacienteResponseDTO actualizar(Long id, PacienteRequestDTO request);
    void eliminar(Long id);
}
