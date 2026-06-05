package husj.referencia.service;

import husj.referencia.model.dto.request.PacienteRequestDTO;
import husj.referencia.model.dto.response.PacienteResponseDTO;

import java.util.List;

public interface PacienteService {
    PacienteResponseDTO crear(PacienteRequestDTO request);
    PacienteResponseDTO obtenerPorId(Long id);
    List<PacienteResponseDTO> listarTodos();
    PacienteResponseDTO actualizar(Long id, PacienteRequestDTO request);
    void eliminar(Long id);
}
