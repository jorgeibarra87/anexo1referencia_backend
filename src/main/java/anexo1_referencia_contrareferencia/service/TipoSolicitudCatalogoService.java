package anexo1_referencia_contrareferencia.service;

import anexo1_referencia_contrareferencia.model.dto.request.TipoSolicitudCatalogoRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.TipoSolicitudCatalogoResponseDTO;

import java.util.List;

public interface TipoSolicitudCatalogoService {
    TipoSolicitudCatalogoResponseDTO crear(TipoSolicitudCatalogoRequestDTO request);
    TipoSolicitudCatalogoResponseDTO obtenerPorId(Integer id);
    List<TipoSolicitudCatalogoResponseDTO> listarTodos();
    List<TipoSolicitudCatalogoResponseDTO> listarActivos();
    TipoSolicitudCatalogoResponseDTO actualizar(Integer id, TipoSolicitudCatalogoRequestDTO request);
    void eliminar(Integer id);
}
