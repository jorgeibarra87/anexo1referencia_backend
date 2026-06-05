package husj.referencia.repository;

import husj.referencia.model.entity.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    //Optional<Tramite> findByNumeroTramite(String numeroTramite);
    List<Tramite> findByPacienteId(Long pacienteId);
    List<Tramite> findByFechaTramiteBetween(LocalDateTime inicio, LocalDateTime fin);
}
