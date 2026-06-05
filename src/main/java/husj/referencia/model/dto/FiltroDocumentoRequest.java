package husj.referencia.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FiltroDocumentoRequest {

    @NotBlank(message = "El documento es requerido")
    @Size(max = 50, message = "El documento no puede exceder 50 caracteres")
    private String documento;

    // Constructores
    public FiltroDocumentoRequest() {}

    public FiltroDocumentoRequest(String documento) {
        this.documento = documento;
    }

    // Getters y Setters
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
