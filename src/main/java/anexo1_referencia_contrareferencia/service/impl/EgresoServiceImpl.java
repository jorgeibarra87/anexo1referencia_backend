package anexo1_referencia_contrareferencia.service.impl;

import anexo1_referencia_contrareferencia.model.dto.request.EgresoRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.EgresoResponseDTO;
import anexo1_referencia_contrareferencia.model.entity.Egreso;
import anexo1_referencia_contrareferencia.model.entity.Tramite;
import anexo1_referencia_contrareferencia.repository.EgresoRepository;
import anexo1_referencia_contrareferencia.repository.TramiteRepository;
import anexo1_referencia_contrareferencia.service.EgresoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EgresoServiceImpl implements EgresoService {

    private final EgresoRepository egresoRepository;
    private final TramiteRepository tramiteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public EgresoResponseDTO crear(EgresoRequestDTO request) {
        Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));

        Egreso entity = modelMapper.map(request, Egreso.class);
        entity.setTramite(tramite);

        return modelMapper.map(egresoRepository.save(entity), EgresoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public EgresoResponseDTO obtenerPorId(Long id) {
        Egreso entity = egresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Egreso no encontrado con id: " + id));
        return modelMapper.map(entity, EgresoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.Optional<EgresoResponseDTO> obtenerPorTramiteId(Long tramiteId) {
        return egresoRepository.findByTramiteId(tramiteId)
                .map(entity -> modelMapper.map(entity, EgresoResponseDTO.class));
    }

    @Override
    public EgresoResponseDTO actualizar(Long id, EgresoRequestDTO request) {
        Egreso entity = egresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Egreso no encontrado con id: " + id));

        if (request.getTramiteId() != null) {
            Tramite tramite = tramiteRepository.findById(request.getTramiteId())
                    .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + request.getTramiteId()));
            entity.setTramite(tramite);
        }

        modelMapper.map(request, entity);
        return modelMapper.map(egresoRepository.save(entity), EgresoResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!egresoRepository.existsById(id)) {
            throw new EntityNotFoundException("Egreso no encontrado con id: " + id);
        }
        egresoRepository.deleteById(id);
    }
}
