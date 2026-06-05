package husj.referencia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ref_hospitales")
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospital")
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "ciudad", nullable = false)
    private String ciudad;

}
