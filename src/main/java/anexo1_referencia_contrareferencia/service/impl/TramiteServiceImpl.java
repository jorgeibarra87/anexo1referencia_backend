package anexo1_referencia_contrareferencia.service.impl;

import anexo1_referencia_contrareferencia.model.dto.request.TramiteRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.TramiteResponseDTO;
import anexo1_referencia_contrareferencia.model.entity.Paciente;
import anexo1_referencia_contrareferencia.model.entity.TipoSolicitudCatalogo;
import anexo1_referencia_contrareferencia.model.entity.Tramite;
import anexo1_referencia_contrareferencia.repository.PacienteRepository;
import anexo1_referencia_contrareferencia.repository.TipoSolicitudCatalogoRepository;
import anexo1_referencia_contrareferencia.repository.TramiteRepository;
import anexo1_referencia_contrareferencia.service.TramiteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TramiteServiceImpl implements TramiteService {

    private final TramiteRepository tramiteRepository;
    private final PacienteRepository pacienteRepository;
    private final TipoSolicitudCatalogoRepository tipoSolicitudCatalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TramiteResponseDTO crear(TramiteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + request.getPacienteId()));

        Tramite entity = new Tramite();
        entity.setNumeroTramite(request.getNumeroTramite());
        entity.setPaciente(paciente);
        entity.setTipoIngreso(request.getTipoIngreso());
        entity.setServicioOrigen(request.getServicioOrigen());
        entity.setDescripcion(request.getDescripcion());
        entity.setAuxiliarReferencia(request.getAuxiliarReferencia());
        if (request.getEstado() != null) {
            entity.setEstado(request.getEstado());
        }
        if (request.getTipoSolicitudId() != null) {
            TipoSolicitudCatalogo catalogo = tipoSolicitudCatalogoRepository.findById(request.getTipoSolicitudId())
                    .orElseThrow(() -> new EntityNotFoundException("TipoSolicitud no encontrado con id: " + request.getTipoSolicitudId()));
            entity.setTipoSolicitud(catalogo);
        }

        Tramite guardado = tramiteRepository.save(entity);
        return toResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public TramiteResponseDTO obtenerPorId(Long id) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));
        return toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TramiteResponseDTO> listarTodos() {
        return tramiteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TramiteResponseDTO actualizar(Long id, TramiteRequestDTO request) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));

        if (request.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + request.getPacienteId()));
            entity.setPaciente(paciente);
        }
        if (request.getTipoSolicitudId() != null) {
            TipoSolicitudCatalogo catalogo = tipoSolicitudCatalogoRepository.findById(request.getTipoSolicitudId())
                    .orElseThrow(() -> new EntityNotFoundException("TipoSolicitud no encontrado con id: " + request.getTipoSolicitudId()));
            entity.setTipoSolicitud(catalogo);
        }

        modelMapper.map(request, entity);
        return toResponseDTO(tramiteRepository.save(entity));
    }

    @Override
    public TramiteResponseDTO cambiarEstado(Long id, String estado) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));
        entity.setEstado(Tramite.Estado.valueOf(estado));
        return toResponseDTO(tramiteRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!tramiteRepository.existsById(id)) {
            throw new EntityNotFoundException("Tramite no encontrado con id: " + id);
        }
        tramiteRepository.deleteById(id);
    }

    private TramiteResponseDTO toResponseDTO(Tramite entity) {
        TramiteResponseDTO dto = modelMapper.map(entity, TramiteResponseDTO.class);
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setPacienteNombre(entity.getPaciente().getNombreCompleto());
        if (entity.getTipoSolicitud() != null) {
            dto.setTipoSolicitudId(entity.getTipoSolicitud().getId());
            dto.setTipoSolicitudDescripcion(entity.getTipoSolicitud().getDescripcion());
        }
        return dto;
    }
}
