package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    Optional<Tramite> findByNumeroTramite(String numeroTramite);
    List<Tramite> findByPacienteId(Long pacienteId);
    List<Tramite> findByFechaTramiteBetween(LocalDateTime inicio, LocalDateTime fin);
}
