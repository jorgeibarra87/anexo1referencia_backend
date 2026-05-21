package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento_ambulatorio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoAmbulatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tramite_id", nullable = false)
    private Tramite tramite;

    @Column(name = "fecha_nota", nullable = false)
    private LocalDateTime fechaNota;

    @Column(name = "nota_seguimiento", nullable = false, columnDefinition = "TEXT")
    private String notaSeguimiento;

    @Column(nullable = false, length = 50)
    @Builder.Default
    private String estado = "ACTIVO";

    @Column(name = "usuario", length = 200)
    private String usuario;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        if (fechaNota == null) fechaNota = LocalDateTime.now();
    }
}