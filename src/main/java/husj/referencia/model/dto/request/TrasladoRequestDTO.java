package husj.referencia.model.dto.request;

import husj.referencia.model.enums.Archivo;
import husj.referencia.model.enums.TipoTraslado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrasladoRequestDTO {

    @NotNull(message = "La fecha de traslado es obligatoria")
    private LocalDateTime fechaTraslado;
    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nomPaciente;
    @NotBlank(message = "El número de documento es obligatorio")
    private String documento;
    @NotBlank(message = "El número de ingreso es obligatorio")
    private String ingreso;
    @NotBlank(message = "La EPS es obligatoria")
    private String eps;
    @NotNull(message = "El tipo de traslado es obligatorio")
    private TipoTraslado tipoTraslado;
    @NotBlank(message = "El servicio es obligatorio")
    private String servicio;
    @NotBlank(message = "El destino es obligatorio")
    private String destino;
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    private String autorizacion;
    private String auxiliarReferencia;
    private String auxiliarAmbulancia;
    private List<String> medicamentos;
    @NotNull(message = "El archivo es obligatorio")
    private Archivo archivo;
    private String observaciones;
    private String estado;
}
