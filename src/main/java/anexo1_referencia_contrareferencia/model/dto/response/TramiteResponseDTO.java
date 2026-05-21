package anexo1_referencia_contrareferencia.model.dto.response;

import anexo1_referencia_contrareferencia.model.entity.Tramite;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TramiteResponseDTO {
    private Long id;
    private String numeroTramite;
    private Long pacienteId;
    private String pacienteNombre;
    private LocalDateTime fechaTramite;
    private Tramite.TipoIngreso tipoIngreso;
    private String servicioOrigen;
    private Integer tipoSolicitudId;
    private String tipoSolicitudDescripcion;
    private String descripcion;
    private Tramite.Estado estado;
    private String auxiliarReferencia;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
