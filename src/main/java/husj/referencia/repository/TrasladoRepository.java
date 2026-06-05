package husj.referencia.repository;

import husj.referencia.model.entity.Traslado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrasladoRepository extends JpaRepository<Traslado, Long> {
    List<Traslado> findByFechaTrasladoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
