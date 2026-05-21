package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_solicitud_catalog")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoSolicitudCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(nullable = false, unique = true, length = 30)
//    private String codigo;

    @Column(nullable = false, length = 120)
    private String descripcion;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}