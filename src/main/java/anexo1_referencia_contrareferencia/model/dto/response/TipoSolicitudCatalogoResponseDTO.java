package anexo1_referencia_contrareferencia.model.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoSolicitudCatalogoResponseDTO {
    private Integer id;
    private String descripcion;
    private Boolean activo;
}
