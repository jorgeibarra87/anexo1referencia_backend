package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.SeguimientoAmbulatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoAmbulatorioRepository extends JpaRepository<SeguimientoAmbulatorio, Long> {
    List<SeguimientoAmbulatorio> findByTramiteIdOrderByFechaNotaDesc(Long tramiteId);
}
