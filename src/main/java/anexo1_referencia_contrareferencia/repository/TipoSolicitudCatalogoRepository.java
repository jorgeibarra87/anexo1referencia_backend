package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.TipoSolicitudCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoSolicitudCatalogoRepository extends JpaRepository<TipoSolicitudCatalogo, Integer> {
    List<TipoSolicitudCatalogo> findByActivoTrue();
}
