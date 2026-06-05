package husj.referencia.service;

import husj.referencia.model.dto.request.IngresoReqDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IngresoService {
    Page<IngresoReqDTO> ListarIngreso(int page, int size);

    Optional<IngresoReqDTO> buscarPorId(Long id);

    IngresoReqDTO guardar(IngresoReqDTO ingreso);

    void eliminar(Long id);
}
