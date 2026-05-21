package anexo1_referencia_contrareferencia.repository;

import anexo1_referencia_contrareferencia.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByTipoDocumentoAndNumeroDocumento(
            Paciente.TipoDocumento tipoDocumento, String numeroDocumento);
}
