package husj.referencia.service;

import husj.referencia.model.dto.request.DatosIngresoUpdateReqDTO;
import husj.referencia.model.dto.request.DatosReqDTO;
import husj.referencia.model.dto.response.DatosResDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DatosService {

    Page<DatosResDTO> listarDatos(int page, int size);
    List<DatosResDTO> listarDatosPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin);
    DatosResDTO guardar(DatosReqDTO datos);
    void actualizarFechasRegistroParaBuscarIngresos(List<Long> ids);
    List<DatosResDTO> actualizarIngresos(List<DatosIngresoUpdateReqDTO> datos);
    DatosResDTO actualizarComentarioTriage(Long id, String comentario);
    List<DatosResDTO> buscarPorIdODocumento(String valor);

}
