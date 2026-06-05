package husj.referencia.model.dto.request;

import husj.referencia.model.enums.EstadoReferenciaEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatosReqDTO {
    private String nombres;
    private String apellidos;
    private String identificacion;
    private Integer edad;
    private String sexo;
    private String entidadSocial;
    private Long hospital_id;
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
    private List<IngresoReqDTO> ingresos;
}
