package husj.referencia.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturacionResponseDTO {

    private Long id;
    private Long trasladoId;
    private String documento;
    private String nomPaciente;
    private String ingreso;
    private String servicio;
    private String produccion;
    private LocalDateTime fechaFactura;
    private String factura;
    private Double valor;
    private String nombreFacturador;
    private String estado;
}
