package husj.referencia.service.impl;

import husj.referencia.model.dto.request.SeguimientoAmbulatorioRequestDTO;
import husj.referencia.model.dto.response.SeguimientoAmbulatorioResponseDTO;
import husj.referencia.model.entity.SeguimientoAmbulatorio;
import husj.referencia.model.entity.Tramite;
import husj.referencia.repository.SeguimientoAmbulatorioRepository;
import husj.referencia.repository.TramiteRepository;
import husj.referencia.service.SeguimientoAmbulatorioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SeguimientoAmbulatorioServiceImpl implements SeguimientoAmbulatorioService {

    private final SeguimientoAmbulatorioRepository repository;
    private final TramiteRepository tramiteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SeguimientoAmbulatorioResponseDTO crear(SeguimientoAmbulatorioRequestDTO request) {
        if (repository.existsByTramiteId(request.getTramiteId())) {
            throw new IllegalStateException("Ya existe un seguimiento ambulatorio para este trámite");
        }
        Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));

        SeguimientoAmbulatorio entity = SeguimientoAmbulatorio.builder()
                .tramite(tramite)
                .fechaNota(request.getFechaNota() != null ? request.getFechaNota() : java.time.LocalDateTime.now())
                .notaSeguimiento(request.getNotaSeguimiento())
                .estado(request.getEstado() != null ? request.getEstado() : "ACTIVO")
                .auxiliarReferencia(request.getAuxiliarReferencia())
                .build();

        return modelMapper.map(repository.save(entity), SeguimientoAmbulatorioResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public SeguimientoAmbulatorioResponseDTO obtenerPorId(Long id) {
        SeguimientoAmbulatorio entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SeguimientoAmbulatorio no encontrado con id: " + id));
        return modelMapper.map(entity, SeguimientoAmbulatorioResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoAmbulatorioResponseDTO> listarPorTramiteId(Long tramiteId) {
        return repository.findByTramiteIdOrderByFechaNotaDesc(tramiteId).stream()
                .map(entity -> modelMapper.map(entity, SeguimientoAmbulatorioResponseDTO.class))
                .toList();
    }

    @Override
    public SeguimientoAmbulatorioResponseDTO actualizar(Long id, SeguimientoAmbulatorioRequestDTO request) {
        SeguimientoAmbulatorio entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SeguimientoAmbulatorio no encontrado con id: " + id));

        if (request.getTramiteId() != null) {
            Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                    .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));
            entity.setTramite(tramite);
        }

        if (request.getNotaSeguimiento() != null) entity.setNotaSeguimiento(request.getNotaSeguimiento());
        if (request.getEstado() != null) entity.setEstado(request.getEstado());
        if (request.getAuxiliarReferencia() != null) entity.setAuxiliarReferencia(request.getAuxiliarReferencia());
        return modelMapper.map(repository.save(entity), SeguimientoAmbulatorioResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("SeguimientoAmbulatorio no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
