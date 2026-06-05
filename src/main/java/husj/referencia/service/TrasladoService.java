package husj.referencia.service;

import husj.referencia.model.dto.request.TrasladoRequestDTO;
import husj.referencia.model.dto.response.TrasladoResponseDTO;

import java.util.List;

public interface TrasladoService {

    TrasladoResponseDTO crear(TrasladoRequestDTO request);

    TrasladoResponseDTO obtenerPorId(Long id);

    List<TrasladoResponseDTO> listarTodos();

    TrasladoResponseDTO actualizar(Long id, TrasladoRequestDTO request);

    TrasladoResponseDTO cambiarEstado(Long id, String estado);

    void eliminar(Long id);
}
