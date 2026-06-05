package husj.referencia.model.dto.response;

import husj.referencia.model.entity.Paciente;
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
