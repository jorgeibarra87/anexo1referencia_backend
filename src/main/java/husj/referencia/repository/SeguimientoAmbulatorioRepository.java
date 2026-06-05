package husj.referencia.repository;

import husj.referencia.model.entity.SeguimientoAmbulatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoAmbulatorioRepository extends JpaRepository<SeguimientoAmbulatorio, Long> {
    List<SeguimientoAmbulatorio> findByTramiteIdOrderByFechaNotaDesc(Long tramiteId);
}
