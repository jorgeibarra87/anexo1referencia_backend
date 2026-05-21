package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "egreso")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Egreso {

    public enum TipoEgreso { ALTA_MEDICA, TRASLADO, VOLUNTARIA, DEFUNCION, OTRO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tramite_id", nullable = false, unique = true)
    private Tramite tramite;

    @Column(name = "servicio_egreso", length = 100)
    private String servicioEgreso;

    @Column(name = "fecha_egreso", nullable = false)
    private LocalDateTime fechaEgreso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_egreso", nullable = false, length = 20)
    @Builder.Default
    private TipoEgreso tipoEgreso = TipoEgreso.ALTA_MEDICA;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() { createdAt = LocalDateTime.now(); }
}