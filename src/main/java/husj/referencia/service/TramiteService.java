package husj.referencia.service;

import husj.referencia.model.dto.request.TramiteRequestDTO;
import husj.referencia.model.dto.response.TramiteCompletoResponseDTO;
import husj.referencia.model.dto.response.TramiteResponseDTO;

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
