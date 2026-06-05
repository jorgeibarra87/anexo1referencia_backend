package husj.referencia.repository;

import husj.referencia.model.entity.CuentaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaMedicaRepository extends JpaRepository<CuentaMedica, Long> {
    List<CuentaMedica> findByTrasladoId(Long trasladoId);
    List<CuentaMedica> findByTrasladoIdOrderByFechaCuentaDesc(Long trasladoId);

}
