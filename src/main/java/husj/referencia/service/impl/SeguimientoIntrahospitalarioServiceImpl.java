package husj.referencia.service.impl;

import husj.referencia.model.dto.request.SeguimientoIntrahospitalarioRequestDTO;
import husj.referencia.model.dto.response.SeguimientoIntrahospitalarioResponseDTO;
import husj.referencia.model.entity.SeguimientoIntrahospitalario;
import husj.referencia.model.entity.SeguimientoIntrahospitalario.EstadoAutorizacion;
import husj.referencia.model.entity.Tramite;
import husj.referencia.repository.SeguimientoIntrahospitalarioRepository;
import husj.referencia.repository.TramiteRepository;
import husj.referencia.service.SeguimientoIntrahospitalarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SeguimientoIntrahospitalarioServiceImpl implements SeguimientoIntrahospitalarioService {

    private final SeguimientoIntrahospitalarioRepository repository;
    private final TramiteRepository tramiteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SeguimientoIntrahospitalarioResponseDTO crear(SeguimientoIntrahospitalarioRequestDTO request) {
        Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));

        SeguimientoIntrahospitalario entity = SeguimientoIntrahospitalario.builder()
                .tramite(tramite)
                .fechaSeguimiento(request.getFechaSeguimiento() != null ? request.getFechaSeguimiento() : LocalDateTime.now())
                .autorizacion(request.getAutorizacion())
                .estadoAutorizacion(request.getEstadoAutorizacion() != null ? request.getEstadoAutorizacion() : EstadoAutorizacion.PENDIENTE)
                .auxiliarReferencia(request.getAuxiliarReferencia())
                .build();

        return modelMapper.map(repository.save(entity), SeguimientoIntrahospitalarioResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public SeguimientoIntrahospitalarioResponseDTO obtenerPorId(Long id) {
        SeguimientoIntrahospitalario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SeguimientoIntrahospitalario no encontrado con id: " + id));
        return modelMapper.map(entity, SeguimientoIntrahospitalarioResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoIntrahospitalarioResponseDTO> listarPorTramiteId(Long tramiteId) {
        return repository.findByTramiteIdOrderByFechaSeguimientoDesc(tramiteId).stream()
                .map(entity -> modelMapper.map(entity, SeguimientoIntrahospitalarioResponseDTO.class))
                .toList();
    }

    @Override
    public SeguimientoIntrahospitalarioResponseDTO actualizar(Long id, SeguimientoIntrahospitalarioRequestDTO request) {
        SeguimientoIntrahospitalario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SeguimientoIntrahospitalario no encontrado con id: " + id));

        if (request.getTramiteId() != null) {
            Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                    .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));
            entity.setTramite(tramite);
        }

        if (request.getFechaSeguimiento() != null) entity.setFechaSeguimiento(request.getFechaSeguimiento());
        if (request.getAutorizacion() != null) entity.setAutorizacion(request.getAutorizacion());
        if (request.getEstadoAutorizacion() != null) entity.setEstadoAutorizacion(request.getEstadoAutorizacion());
        if (request.getAuxiliarReferencia() != null) entity.setAuxiliarReferencia(request.getAuxiliarReferencia());

        return modelMapper.map(repository.save(entity), SeguimientoIntrahospitalarioResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("SeguimientoIntrahospitalario no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
