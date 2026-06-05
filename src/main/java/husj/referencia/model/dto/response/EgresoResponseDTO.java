package husj.referencia.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EgresoResponseDTO {
    private Long id;
    private Long tramiteId;
    private String servicioEgreso;
    private LocalDateTime fechaEgreso;
    private String observaciones;
    private LocalDateTime createdAt;
}
