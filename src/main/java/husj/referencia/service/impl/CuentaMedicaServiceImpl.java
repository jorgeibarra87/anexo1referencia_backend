package husj.referencia.service.impl;

import husj.referencia.model.dto.request.CuentaMedicaRequestDTO;
import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;
import husj.referencia.model.entity.CuentaMedica;
import husj.referencia.model.entity.Traslado;
import husj.referencia.repository.CuentaMedicaRepository;
import husj.referencia.repository.TrasladoRepository;
import husj.referencia.service.CuentaMedicaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CuentaMedicaServiceImpl implements CuentaMedicaService {

    private final CuentaMedicaRepository cuentaMedicaRepository;
    private final TrasladoRepository trasladoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CuentaMedicaResponseDTO crear(CuentaMedicaRequestDTO request) {
        Traslado traslado = trasladoRepository.findById(request.getTrasladoId())
                .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado"));

        CuentaMedica entity = new CuentaMedica();
        entity.setFechaCuenta(request.getFechaCuenta());
        entity.setServicioEgreso(request.getServicioEgreso());
        entity.setResponsableAuditoria(request.getResponsableAuditoria());
        entity.setObservaciones(request.getObservaciones());
        entity.setTraslado(traslado);
        entity.setFechaEgreso(request.getFechaEgreso());
        entity.setEstado(request.getEstado() != null ? request.getEstado() : "PENDIENTE");
        CuentaMedica guardada = cuentaMedicaRepository.save(entity);
        return modelMapper.map(guardada, CuentaMedicaResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaMedicaResponseDTO obtenerPorId(Long id) {
        CuentaMedica entity = cuentaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta médica no encontrada con id: " + id));
        return modelMapper.map(entity, CuentaMedicaResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaMedicaResponseDTO> listarTodos() {
        return cuentaMedicaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CuentaMedicaResponseDTO.class))
                .toList();
    }

    @Override
    public CuentaMedicaResponseDTO actualizar(Long id, CuentaMedicaRequestDTO request) {
        CuentaMedica entity = cuentaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta médica no encontrada con id: " + id));

        if (request.getTrasladoId() != null && !request.getTrasladoId().equals(entity.getTraslado().getId())) {
            Traslado traslado = trasladoRepository.findById(request.getTrasladoId())
                    .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + request.getTrasladoId()));
            entity.setTraslado(traslado);
        }
        entity.setFechaCuenta(request.getFechaCuenta());
        entity.setServicioEgreso(request.getServicioEgreso());
        entity.setResponsableAuditoria(request.getResponsableAuditoria());
        entity.setObservaciones(request.getObservaciones());
        entity.setFechaEgreso(request.getFechaEgreso());

        CuentaMedica actualizada = cuentaMedicaRepository.save(entity);
        return modelMapper.map(actualizada, CuentaMedicaResponseDTO.class);
    }

    @Override
    public CuentaMedicaResponseDTO cambiarEstado(Long id, String estado) {
        CuentaMedica entity = cuentaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cuenta no encontrada con id: " + id));
        entity.setEstado(estado);
        return modelMapper.map(cuentaMedicaRepository.save(entity), CuentaMedicaResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!cuentaMedicaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cuenta médica no encontrada con id: " + id);
        }
        cuentaMedicaRepository.deleteById(id);
    }
}
