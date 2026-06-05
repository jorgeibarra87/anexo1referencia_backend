package husj.referencia.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IntraResumenDTO {
    private LocalDateTime fechaSeguimiento;
    private String autorizacion;
    private String estadoAutorizacion;
    private String auxiliarReferencia;
}
