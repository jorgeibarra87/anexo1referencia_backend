package husj.referencia.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturacionRequestDTO {

    @NotNull(message = "El ID del traslado es obligatorio")
    private Long trasladoId;
    private String produccion;
    private LocalDateTime fechaFactura;
    private String factura;
    private Double valor;
    private String nombreFacturador;
    private String estado;
}
