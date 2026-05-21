package anexo1_referencia_contrareferencia.model.dto.response;

import anexo1_referencia_contrareferencia.model.entity.Egreso;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EgresoResponseDTO {
    private Long id;
    private Long tramiteId;
    private String servicioEgreso;
    private LocalDateTime fechaEgreso;
    private Egreso.TipoEgreso tipoEgreso;
    private String observaciones;
    private LocalDateTime createdAt;
}
