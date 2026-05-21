package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento_intrahospitalario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeguimientoIntrahospitalario {

    public enum EstadoAutorizacion { PENDIENTE, AUTORIZADO, NEGADO, EN_TRAMITE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tramite_id", nullable = false)
    private Tramite tramite;

    @Column(name = "fecha_seguimiento", nullable = false)
    private LocalDateTime fechaSeguimiento;

    @Column(name = "prestador_autorizado", length = 200)
    private String prestadorAutorizado;

    @Column(name = "numero_autorizacion", length = 50)
    private String numeroAutorizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_autorizacion", nullable = false, length = 20)
    @Builder.Default
    private EstadoAutorizacion estadoAutorizacion = EstadoAutorizacion.PENDIENTE;

    @Column(name = "auxiliar_referencia", length = 200)
    private String auxiliarReferencia;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        if (fechaSeguimiento == null) fechaSeguimiento = LocalDateTime.now();
    }
}