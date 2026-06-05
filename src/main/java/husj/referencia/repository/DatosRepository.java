package husj.referencia.repository;

import husj.referencia.model.entity.Datos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DatosRepository extends JpaRepository<Datos, Long> {

    Page<Datos> findAllByOrderByIdDesc(Pageable pageable);

    // filtrar por el campo 'fecha'
    @Query("SELECT DISTINCT d FROM Datos d LEFT JOIN FETCH d.ingresos WHERE d.fechaComentario BETWEEN :fechaInicio AND :fechaFin")
    List<Datos> findByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // método para búsqueda por identificación
    @Query("SELECT DISTINCT d FROM Datos d LEFT JOIN FETCH d.ingresos WHERE d.identificacion = :identificacion")
    List<Datos> findByIdentificacion(@Param("identificacion") String identificacion);

    @Query("SELECT d FROM Datos d LEFT JOIN FETCH d.ingresos WHERE d.id IN :ids")
    List<Datos> findAllByIdWithIngresos(List<Long> ids);
}