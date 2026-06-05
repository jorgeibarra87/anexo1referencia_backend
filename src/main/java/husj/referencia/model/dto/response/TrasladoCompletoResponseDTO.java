package husj.referencia.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrasladoCompletoResponseDTO {

    private TrasladoResponseDTO traslado;
    private List<FacturacionResponseDTO> facturaciones;
    private List<CuentaMedicaResponseDTO> cuentasMedicas;
}
