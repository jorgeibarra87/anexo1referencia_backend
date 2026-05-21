package anexo1_referencia_contrareferencia.service.impl;

import anexo1_referencia_contrareferencia.model.dto.request.TipoSolicitudCatalogoRequestDTO;
import anexo1_referencia_contrareferencia.model.dto.response.TipoSolicitudCatalogoResponseDTO;
import anexo1_referencia_contrareferencia.model.entity.TipoSolicitudCatalogo;
import anexo1_referencia_contrareferencia.repository.TipoSolicitudCatalogoRepository;
import anexo1_referencia_contrareferencia.service.TipoSolicitudCatalogoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoSolicitudCatalogoServiceImpl implements TipoSolicitudCatalogoService {

    private final TipoSolicitudCatalogoRepository repository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TipoSolicitudCatalogoResponseDTO crear(TipoSolicitudCatalogoRequestDTO request) {
        TipoSolicitudCatalogo entity = modelMapper.map(request, TipoSolicitudCatalogo.class);
        return modelMapper.map(repository.save(entity), TipoSolicitudCatalogoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoSolicitudCatalogoResponseDTO obtenerPorId(Integer id) {
        TipoSolicitudCatalogo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoSolicitudCatalogo no encontrado con id: " + id));
        return modelMapper.map(entity, TipoSolicitudCatalogoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoSolicitudCatalogoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TipoSolicitudCatalogoResponseDTO.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoSolicitudCatalogoResponseDTO> listarActivos() {
        return repository.findByActivoTrue().stream()
                .map(entity -> modelMapper.map(entity, TipoSolicitudCatalogoResponseDTO.class))
                .toList();
    }

    @Override
    public TipoSolicitudCatalogoResponseDTO actualizar(Integer id, TipoSolicitudCatalogoRequestDTO request) {
        TipoSolicitudCatalogo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoSolicitudCatalogo no encontrado con id: " + id));
        modelMapper.map(request, entity);
        return modelMapper.map(repository.save(entity), TipoSolicitudCatalogoResponseDTO.class);
    }

    @Override
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("TipoSolicitudCatalogo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
