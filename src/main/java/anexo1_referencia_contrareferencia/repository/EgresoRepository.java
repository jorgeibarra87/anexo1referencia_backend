package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EgresoRepository extends JpaRepository<Egreso, Long> {
    Optional<Egreso> findByTramiteId(Long tramiteId);
}
