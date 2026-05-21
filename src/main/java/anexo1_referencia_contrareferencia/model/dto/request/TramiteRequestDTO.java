package anexo1_referencia_contrareferencia.model.dto.request;

import anexo1_referencia_contrareferencia.model.entity.Tramite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TramiteRequestDTO {

    @NotBlank(message = "El número de trámite es obligatorio")
    private String numeroTramite;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    private LocalDateTime fechaTramite;

    private Tramite.TipoIngreso tipoIngreso;

    private String servicioOrigen;

    private Integer tipoSolicitudId;

    private String descripcion;

    private Tramite.Estado estado;

    private String auxiliarReferencia;
}
