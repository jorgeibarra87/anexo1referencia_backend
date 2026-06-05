package husj.referencia.model.dto.response;

import husj.referencia.model.enums.Archivo;
import husj.referencia.model.enums.TipoTraslado;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrasladoResponseDTO {

    private Long id;
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
    private  String observaciones;
    private  String estado;
}
