package husj.referencia.repository;

import husj.referencia.model.entity.SeguimientoIntrahospitalario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoIntrahospitalarioRepository extends JpaRepository<SeguimientoIntrahospitalario, Long> {
    List<SeguimientoIntrahospitalario> findByTramiteIdOrderByFechaSeguimientoDesc(Long tramiteId);
}
