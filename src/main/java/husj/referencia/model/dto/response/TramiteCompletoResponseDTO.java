package husj.referencia.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TramiteCompletoResponseDTO {
    private Long id;
    private LocalDateTime fechaTramite;
    private String pacienteNombre;
    private String pacienteDocumento;
    private String ingreso;
    private String pacienteEps;
    private String servicio;
    private String tipoSolicitudDescripcion;
    private String descripcion;
    private String estado;
    private String auxiliarReferencia;
    private LocalDateTime intraFechaSeguimiento;
    private String intraAutorizacion;
    private String intraEstadoAutorizacion;
    private String intraAuxiliarReferencia;
    private List<IntraResumenDTO> intraSeguimientos;
    private String egresoServicio;
    private LocalDateTime egresoFecha;
    private String ambulatorioNotaSeguimiento;
    private LocalDateTime ambulatorioFechaNota;
}
