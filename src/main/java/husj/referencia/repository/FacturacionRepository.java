package husj.referencia.repository;

import husj.referencia.model.entity.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
    List<Facturacion> findByTrasladoId(Long trasladoId);
    List<Facturacion> findByTrasladoIdOrderByFechaFacturaDesc(Long trasladoId);
}
