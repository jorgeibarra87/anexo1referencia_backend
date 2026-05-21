package anexo1_referencia_contrareferencia.model.dto.request;

import anexo1_referencia_contrareferencia.model.entity.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PacienteRequestDTO {

    @NotNull(message = "El tipo de documento es obligatorio")
    private Paciente.TipoDocumento tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    private String eps;
    private String telefono;
    private String email;
}
