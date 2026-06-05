package husj.referencia.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaMedicaRequestDTO {

    @NotNull(message = "El ID del traslado es obligatorio")
    private Long trasladoId;
    @NotNull(message = "La fecha de cuenta es obligatoria")
    private LocalDateTime fechaCuenta;
    private String servicioEgreso;
    private String responsableAuditoria;
    private String observaciones;
    private String estado;
    private LocalDateTime fechaEgreso;
}
