package husj.referencia.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatosIngresoUpdateReqDTO {

    private Long id;
    private List<IngresoReqDTO> ingresos;

}
