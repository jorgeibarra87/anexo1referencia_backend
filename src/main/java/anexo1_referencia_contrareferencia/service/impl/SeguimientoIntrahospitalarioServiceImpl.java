package anexo1_referencia_contrareferencia.service.impl;

import anexo1_referencia_contrareferencia.model.dto.request.SeguimientoIntrahospitalarioRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.SeguimientoIntrahospitalarioResponseDTO;
import anexo1_referencia_contrareferencia.model.entity.SeguimientoIntrahospitalario;
import anexo1_referencia_contrareferencia.model.entity.Tramite;
import anexo1_referencia_contrareferencia.repository.SeguimientoIntrahospitalarioRepository;
import anexo1_referencia_contrareferencia.repository.TramiteRepository;
import anexo1_referencia_contrareferencia.service.SeguimientoIntrahospitalarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        SeguimientoIntrahospitalario entity = modelMapper.map(request, SeguimientoIntrahospitalario.class);
        entity.setTramite(tramite);

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

        modelMapper.map(request, entity);
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
