package anexo1_referencia_contrareferencia.model.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoAmbulatorioResponseDTO {
    private Long id;
    private Long tramiteId;
    private LocalDateTime fechaNota;
    private String notaSeguimiento;
    private String estado;
    private String usuario;
    private LocalDateTime createdAt;
}
