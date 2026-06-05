package anexo1_referencia_contrareferencia.model.dto.request;

import anexo1_referencia_contrareferencia.model.entity.SeguimientoIntrahospitalario;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoIntrahospitalarioRequestDTO {

    @NotNull(message = "El ID del trámite es obligatorio")
    private Long tramiteId;

    private LocalDateTime fechaSeguimiento;

    private String autorizacion;

    private SeguimientoIntrahospitalario.EstadoAutorizacion estadoAutorizacion;

    private String auxiliarReferencia;

    private String observaciones;
}
