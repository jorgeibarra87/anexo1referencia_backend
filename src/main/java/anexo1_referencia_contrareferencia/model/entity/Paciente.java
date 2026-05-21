package anexo1_referencia_contrareferencia.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "paciente",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_paciente_documento",
                columnNames = {"tipo_documento", "numero_documento"}
        )
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Paciente {

    public enum TipoDocumento { CC, TI, RC, CE, PA, NIT, OTRO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 10)
    @Builder.Default
    private TipoDocumento tipoDocumento = TipoDocumento.CC;

    @Column(name = "numero_documento", nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "nombre_completo", nullable = false, length = 200)
    private String nombreCompleto;

    @Column(length = 100)
    private String eps;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Tramite> tramites = new ArrayList<>();

    @PrePersist
    void prePersist() { createdAt = LocalDateTime.now(); }
}
