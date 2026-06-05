package husj.referencia.service;

import husj.referencia.model.dto.request.FacturacionRequestDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;

import java.util.List;

public interface FacturacionService {

    FacturacionResponseDTO crear(FacturacionRequestDTO request);

    FacturacionResponseDTO obtenerPorId(Long id);

    List<FacturacionResponseDTO> listarTodos();

    FacturacionResponseDTO actualizar(Long id, FacturacionRequestDTO request);

    FacturacionResponseDTO cambiarEstado(Long id, String estado);

    void eliminar(Long id);
}
