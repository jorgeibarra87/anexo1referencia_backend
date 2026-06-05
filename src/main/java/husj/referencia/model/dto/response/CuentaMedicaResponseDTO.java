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
public class CuentaMedicaResponseDTO {

    private Long id;
    private Long trasladoId;
    private String documento;
    private String nomPaciente;
    private String ingreso;
    private LocalDateTime fechaCuenta;
    private String servicioEgreso;
    private String responsableAuditoria;
    private String observaciones;
    private  String estado;
    private LocalDateTime fechaEgreso;
}

