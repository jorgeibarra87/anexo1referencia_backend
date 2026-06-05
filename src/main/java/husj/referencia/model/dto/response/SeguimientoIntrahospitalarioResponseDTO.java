package husj.referencia.model.dto.response;

import husj.referencia.model.entity.SeguimientoIntrahospitalario;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoIntrahospitalarioResponseDTO {
    private Long id;
    private Long tramiteId;
    private LocalDateTime fechaSeguimiento;
    private String autorizacion;
    private SeguimientoIntrahospitalario.EstadoAutorizacion estadoAutorizacion;
    private String auxiliarReferencia;
    private String observaciones;
    private LocalDateTime createdAt;
}
