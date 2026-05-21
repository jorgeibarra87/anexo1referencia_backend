package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tramite")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tramite {

    public enum Estado { PENDIENTE, EN_PROCESO, CERRADO, ANULADO }
    public enum TipoIngreso { URGENCIAS, HOSPITALIZACION, CONSULTA_EXTERNA, CIRUGIA, OTRO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_tramite", nullable = false, unique = true, length = 30)
    private String numeroTramite;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "fecha_tramite", nullable = false)
    private LocalDateTime fechaTramite;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ingreso", length = 30)
    private TipoIngreso tipoIngreso;

    @Column(name = "servicio_origen", length = 100)
    private String servicioOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_solicitud_id")
    private TipoSolicitudCatalogo tipoSolicitud;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "auxiliar_referencia", length = 255)
    private String auxiliarReferencia;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relaciones hijas
    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SeguimientoIntrahospitalario> seguimientosIntra = new ArrayList<>();

    @OneToOne(mappedBy = "tramite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Egreso egreso;

    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SeguimientoAmbulatorio> seguimientosAmbulatorios = new ArrayList<>();

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (fechaTramite == null) fechaTramite = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() { updatedAt = LocalDateTime.now(); }
}
