package husj.referencia.model.entity;

import husj.referencia.model.enums.EstadoReferenciaEnum;
import husj.referencia.model.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ref_datos")
@Getter
@Setter
public class Datos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ref_datos")
    private Long id;
    @Column(name = "fecha_comentario")
    private LocalDate fechaComentario;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private String identificacion;
    @Column(nullable = false)
    private Integer edad;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum sexo;
    @Column(name = "entidad_social", nullable = false)
    private String entidadSocial;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hospital", nullable = false, foreignKey = @ForeignKey(name = "fk_datos_hospital"))
    private Hospital hospital;
    @Column(name = "medico_sol_remi", nullable = false)
    private String medicoSolicitanteRemision;
    @Column(name = "especialidad_sol_remi", nullable = false)
    private String especialidadSolicitanteRemision;
    @Column(name = "diagnostico_remi", columnDefinition = "TEXT", nullable = false)
    private String diagnosticoRemision;
    @Column(name = "nombre_per_reci_comen", nullable = false)
    private String nombrePersonaRecibeComentario;
    @Column(name = "observaciones", columnDefinition = "TEXT", nullable = false)
    private String observaciones;
    @Column(name = "fc", nullable = false)
    private String fc;
    @Column(name = "fr", nullable = false)
    private String fr;
    @Column(name = "ta",  nullable = false)
    private String ta;
    @Column(name = "temperatura", nullable = false)
    private String temperatura;
    @Column(name = "sodos", nullable = false)
    private String sodos;
    @Column(name = "glasgow", nullable = false)
    private String glasgow;
    @Column(name = "escala_dolor_visual", nullable = false)
    private Integer escalaDolorVisual;
    @Column(name = "requiere_aislamiento", nullable = false)
    private Boolean requiereAislamiento;
    @Column(name = "enviada_urg_vital", nullable = false)
    private Boolean enviadaUrgenciaVital;
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReferenciaEnum estado;
    @Column(name = "causa_remi_nivel_lll", columnDefinition = "TEXT", nullable = false)
    private String causaRemisionNivelLLL;
    @Column(name = "causa_rechazo", columnDefinition = "TEXT")
    private String causaRechazo;
    @Column(name = "nom_med_reg_decision", nullable = false)
    private String nombreMedicoRegistraDecision;
    @OneToMany(mappedBy = "datos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("fechaIngreso DESC")
    private List<Ingreso> ingresos;
    @Column(name ="fecha_actualizacion_busqueda_ingresos")
    private LocalDateTime fechaActualizacionBusquedaIngresos;
    @Column(name = "observacion_triage", columnDefinition = "TEXT")
    private String observacionTriage;
    private Boolean pgp;

    @PrePersist
    public void prePersist() {
        fecha = LocalDateTime.now();
        fechaComentario = LocalDate.now();
    }
}
