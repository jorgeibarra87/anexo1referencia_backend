package husj.referencia.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrasladoCompletoRequestDTO {

    @Valid
    @NotNull(message = "Los datos del traslado son obligatorios")
    private TrasladoRequestDTO traslado;

    @Valid
    private FacturacionRequestDTO facturacion;

    @Valid
    private CuentaMedicaRequestDTO cuentaMedica;
}
