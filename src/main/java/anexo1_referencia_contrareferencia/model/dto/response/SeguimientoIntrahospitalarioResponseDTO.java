package anexo1_referencia_contrareferencia.model.dto.response;

import anexo1_referencia_contrareferencia.model.entity.SeguimientoIntrahospitalario;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoIntrahospitalarioResponseDTO {
    private Long id;
    private Long tramiteId;
    private LocalDateTime fechaSeguimiento;
    private String prestadorAutorizado;
    private String numeroAutorizacion;
    private SeguimientoIntrahospitalario.EstadoAutorizacion estadoAutorizacion;
    private String auxiliarReferencia;
    private String observaciones;
    private LocalDateTime createdAt;
}
