package husj.referencia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta_medica")
@Getter
@Setter
public class CuentaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUENTA_MEDICA")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_TRASLADO", referencedColumnName = "ID_TRASLADO", foreignKey = @ForeignKey(name = "fk_cuenta_medica_traslado"))
    private Traslado traslado;
    @Column(name = "FECHA_CUENTA", nullable = false)
    private LocalDateTime fechaCuenta;
    @Column(name = "SERVICIO_EGRESO", nullable = false, length = 255)
    private String servicioEgreso;
    @Column(name = "RESPONSABLE_AUDITORIA", nullable = false, length = 255)
    private String responsableAuditoria;
    @Column(name = "OBSERVACIONES", columnDefinition = "TEXT")
    private String observaciones;
    @Column(name = "ESTADO", nullable = false)
    private String estado;
    @Column(name = "FECHA_EGRESO")
    private LocalDateTime fechaEgreso;
}
