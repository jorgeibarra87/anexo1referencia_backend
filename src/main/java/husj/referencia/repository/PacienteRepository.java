package husj.referencia.repository;

import husj.referencia.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByTipoDocumentoAndNumeroDocumento(
            Paciente.TipoDocumento tipoDocumento, String numeroDocumento);
}
