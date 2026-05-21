package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.SeguimientoIntrahospitalario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoIntrahospitalarioRepository extends JpaRepository<SeguimientoIntrahospitalario, Long> {
    List<SeguimientoIntrahospitalario> findByTramiteIdOrderByFechaSeguimientoDesc(Long tramiteId);
}
