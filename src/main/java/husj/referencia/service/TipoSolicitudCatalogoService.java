package husj.referencia.service;

import husj.referencia.model.dto.request.TipoSolicitudCatalogoRequestDTO;
import husj.referencia.model.dto.response.TipoSolicitudCatalogoResponseDTO;

import java.util.List;

public interface TipoSolicitudCatalogoService {
    TipoSolicitudCatalogoResponseDTO crear(TipoSolicitudCatalogoRequestDTO request);
    TipoSolicitudCatalogoResponseDTO obtenerPorId(Integer id);
    List<TipoSolicitudCatalogoResponseDTO> listarTodos();
    List<TipoSolicitudCatalogoResponseDTO> listarActivos();
    TipoSolicitudCatalogoResponseDTO actualizar(Integer id, TipoSolicitudCatalogoRequestDTO request);
    void eliminar(Integer id);
}
