package husj.referencia.repository;

import husj.referencia.model.entity.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EgresoRepository extends JpaRepository<Egreso, Long> {
    Optional<Egreso> findByTramiteId(Long tramiteId);
}
