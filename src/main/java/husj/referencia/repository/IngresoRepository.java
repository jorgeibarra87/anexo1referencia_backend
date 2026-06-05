package husj.referencia.repository;

import husj.referencia.model.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository  extends JpaRepository<Ingreso, Long> {
}
