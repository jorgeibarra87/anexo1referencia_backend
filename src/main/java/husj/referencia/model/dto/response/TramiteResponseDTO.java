package husj.referencia.model.dto.response;

import husj.referencia.model.entity.Tramite;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TramiteResponseDTO {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteDocumento;
    private String pacienteEps;
    private LocalDateTime fechaTramite;
    private String ingreso;
    private String servicio;
    private Integer tipoSolicitudId;
    private String tipoSolicitudDescripcion;
    private String descripcion;
    private Tramite.Estado estado;
    private String auxiliarReferencia;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
