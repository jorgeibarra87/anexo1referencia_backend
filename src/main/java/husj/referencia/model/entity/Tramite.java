package husj.referencia.model.entity;

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
    //public enum Ingreso { URGENCIAS, HOSPITALIZACION, CONSULTA_EXTERNA, CIRUGIA, OTRO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_tramite", nullable = false)
    private LocalDateTime fechaTramite;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(length = 50)
    private String ingreso;

    @Column(name = "servicio", length = 100)
    private String servicio;

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
