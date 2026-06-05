package husj.referencia.model.dto.response;

import husj.referencia.model.dto.request.IngresoReqDTO;
import husj.referencia.model.enums.EstadoReferenciaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DatosResDTO {

    private Long id;
    private LocalDate fechaComentario;
    private LocalDateTime fecha;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private Integer edad;
    private String sexo;
    private String entidadSocial;
    private HospitalResDTO hospital;
    private String medicoSolicitanteRemision;
    private String especialidadSolicitanteRemision;
    private String diagnosticoRemision;
    private String nombrePersonaRecibeComentario;
    private String observaciones;
    private String fc;
    private String fr;
    private String ta;
    private String temperatura;
    private String sodos;
    private String glasgow;
    private Integer escalaDolorVisual;
    private Boolean requiereAislamiento;
    private Boolean enviadaUrgenciaVital;
    private EstadoReferenciaEnum estado;
    private String causaRemisionNivelLLL;
    private String causaRechazo;
    private String nombreMedicoRegistraDecision;
    @Schema(description = "atributo que indica si el registro es antiguo mayor a 5 días para no realizar consulta de sus ingresos")
    private boolean esAntiguo;
    private LocalDateTime fechaActualizacionBusquedaIngresos;
    private String observacionTriage;
    private List<IngresoReqDTO> ingresos;
}
