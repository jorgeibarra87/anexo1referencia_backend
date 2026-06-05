package husj.referencia.service;

import husj.referencia.model.dto.request.CuentaMedicaRequestDTO;
import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;

import java.util.List;

public interface CuentaMedicaService {

    CuentaMedicaResponseDTO crear(CuentaMedicaRequestDTO request);

    CuentaMedicaResponseDTO obtenerPorId(Long id);

    List<CuentaMedicaResponseDTO> listarTodos();

    CuentaMedicaResponseDTO actualizar(Long id, CuentaMedicaRequestDTO request);

    CuentaMedicaResponseDTO cambiarEstado(Long id, String estado);

    void eliminar(Long id);
}
