package anexo1_referencia_contrareferencia.model.dto.response;

import anexo1_referencia_contrareferencia.model.entity.Paciente;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PacienteResponseDTO {
    private Long id;
    private Paciente.TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private String eps;
    private String telefono;
    private String email;
    private LocalDateTime createdAt;
}
