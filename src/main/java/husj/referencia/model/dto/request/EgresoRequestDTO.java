package husj.referencia.model.dto.request;

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

    private String observaciones;
}
