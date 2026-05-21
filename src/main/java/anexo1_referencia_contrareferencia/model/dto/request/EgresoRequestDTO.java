package anexo1_referencia_contrareferencia.model.dto.request;

import anexo1_referencia_contrareferencia.model.entity.Egreso;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EgresoRequestDTO {

    @NotNull(message = "El ID del trámite es obligatorio")
    private Long tramiteId;

    private String servicioEgreso;

    @NotNull(message = "La fecha de egreso es obligatoria")
    private LocalDateTime fechaEgreso;

    private Egreso.TipoEgreso tipoEgreso;

    private String observaciones;
}
