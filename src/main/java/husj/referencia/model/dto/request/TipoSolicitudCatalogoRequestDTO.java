package husj.referencia.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoSolicitudCatalogoRequestDTO {

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private Boolean activo;
}
