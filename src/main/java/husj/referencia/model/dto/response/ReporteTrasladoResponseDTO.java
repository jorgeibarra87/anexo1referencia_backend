package husj.referencia.model.dto.response;

import husj.referencia.model.enums.Archivo;
import husj.referencia.model.enums.TipoTraslado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTrasladoResponseDTO {

    private Long idTraslado;
    private LocalDateTime fechaTraslado;
    private String nomPaciente;
    private String documento;
    private String ingreso;
    private String eps;
    private TipoTraslado tipoTraslado;
    private String servicio;
    private String destino;
    private String ciudad;
    private String autorizacion;
    private String auxiliarReferencia;
    private String auxiliarAmbulancia;
    private List<String> medicamentos;
    private Archivo archivo;
    private  String observacionTraslado;
    private  String estadoTraslado;

    private String produccion;
    private LocalDateTime fechaFactura;
    private String factura;
    private Double valor;
    private String nombreFacturador;
    private String estadoFacturacion;

    private LocalDateTime fechaCuenta;
    private String servicioEgreso;
    private String responsableAuditoria;
    private String observacionCuentas;
    private  String estadoCuenta;
    private LocalDateTime fechaEgreso;
}