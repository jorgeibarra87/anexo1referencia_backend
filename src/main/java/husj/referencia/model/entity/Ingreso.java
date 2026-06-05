package husj.referencia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ref_ingresos")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Long id;
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ref_datos", nullable = false, foreignKey = @ForeignKey(name = "fk_ingreso_datos"))
    private Datos datos;

}