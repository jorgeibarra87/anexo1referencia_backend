package husj.referencia.service.impl;

import husj.referencia.model.dto.request.FacturacionRequestDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;
import husj.referencia.model.entity.Facturacion;
import husj.referencia.model.entity.Traslado;
import husj.referencia.repository.FacturacionRepository;
import husj.referencia.repository.TrasladoRepository;
import husj.referencia.service.FacturacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FacturacionServiceImpl implements FacturacionService {

    private final FacturacionRepository facturacionRepository;
    private final TrasladoRepository trasladoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public FacturacionResponseDTO crear(FacturacionRequestDTO request) {

        Traslado traslado = trasladoRepository.findById(request.getTrasladoId())
                .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + request.getTrasladoId()));
        Facturacion entity = new Facturacion();
        entity.setProduccion(request.getProduccion());
        entity.setFechaFactura(request.getFechaFactura());
        entity.setFactura(request.getFactura());
        entity.setValor(request.getValor());
        entity.setNombreFacturador(request.getNombreFacturador());
        entity.setTraslado(traslado);
        if (request.getEstado() == null || request.getEstado() == ""){
            entity.setEstado("PENDIENTE");
        }else{
            entity.setEstado(request.getEstado());
        }

        Facturacion guardado = facturacionRepository.save(entity);
        return modelMapper.map(guardado, FacturacionResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public FacturacionResponseDTO obtenerPorId(Long id) {
        Facturacion entity = facturacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facturación no encontrada con id: " + id));
        return modelMapper.map(entity, FacturacionResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturacionResponseDTO> listarTodos() {
        return facturacionRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, FacturacionResponseDTO.class))
                .toList();
    }

    @Override
    public FacturacionResponseDTO actualizar(Long id, FacturacionRequestDTO request) {
        Facturacion entity = facturacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facturación no encontrada con id: " + id));

        if (request.getTrasladoId() != null && !request.getTrasladoId().equals(entity.getTraslado().getId())) {
            Traslado traslado = trasladoRepository.findById(request.getTrasladoId())
                    .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + request.getTrasladoId()));
            entity.setTraslado(traslado);
        }
        entity.setProduccion(request.getProduccion());
        entity.setFechaFactura(request.getFechaFactura());
        entity.setFactura(request.getFactura());
        entity.setValor(request.getValor());
        entity.setNombreFacturador(request.getNombreFacturador());

        Facturacion actualizada = facturacionRepository.save(entity);
        return modelMapper.map(actualizada, FacturacionResponseDTO.class);
    }

    @Override
    public  FacturacionResponseDTO cambiarEstado(Long id, String estado) {
        Facturacion entity = facturacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("facturacion no encontrada con id: " + id));
        entity.setEstado(estado);
        return modelMapper.map(facturacionRepository.save(entity),  FacturacionResponseDTO.class);
    }


    @Override
    public void eliminar(Long id) {
        if (!facturacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Facturación no encontrada con id: " + id);
        }
        facturacionRepository.deleteById(id);
    }
}
