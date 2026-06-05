package anexo1_referencia_contrareferencia.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoAmbulatorioRequestDTO {

    @NotNull(message = "El ID del trámite es obligatorio")
    private Long tramiteId;

    private LocalDateTime fechaNota;

    @NotBlank(message = "La nota de seguimiento es obligatoria")
    private String notaSeguimiento;

    private String estado;

    private String auxiliarReferencia;
}
