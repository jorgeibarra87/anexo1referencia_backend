package husj.referencia.repository;

import husj.referencia.model.entity.TipoSolicitudCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoSolicitudCatalogoRepository extends JpaRepository<TipoSolicitudCatalogo, Integer> {
    List<TipoSolicitudCatalogo> findByActivoTrue();
}
