package husj.referencia.service.impl;

import husj.referencia.model.dto.request.TrasladoRequestDTO;
import husj.referencia.model.dto.response.TrasladoResponseDTO;
import husj.referencia.model.entity.Traslado;
import husj.referencia.repository.TrasladoRepository;
import husj.referencia.service.TrasladoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TrasladoServiceImpl implements TrasladoService {

    private final TrasladoRepository trasladoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TrasladoResponseDTO crear(TrasladoRequestDTO request) {
        Traslado entity = new Traslado();
        entity.setFechaTraslado(request.getFechaTraslado());
        entity.setNomPaciente(request.getNomPaciente());
        entity.setDocumento(request.getDocumento());
        entity.setIngreso(request.getIngreso());
        entity.setEps(request.getEps());
        entity.setTipoTraslado(request.getTipoTraslado());
        entity.setServicio(request.getServicio());
        entity.setDestino(request.getDestino());
        entity.setCiudad(request.getCiudad());
        entity.setAutorizacion(request.getAutorizacion());
        entity.setAuxiliarReferencia(request.getAuxiliarReferencia());
        entity.setAuxiliarAmbulancia(request.getAuxiliarAmbulancia());
        entity.setMedicamentos(request.getMedicamentos());
        entity.setArchivo(request.getArchivo());
        entity.setObservaciones(request.getObservaciones());
        if (request.getEstado() == null || request.getEstado() == ""){
            entity.setEstado("PENDIENTE");
        }else{
            entity.setEstado(request.getEstado());
        }
        if(entity.getIngreso().equals("SIN INGRESO")){
            entity.setIngreso(null);
        }
        Traslado guardado = trasladoRepository.save(entity);
        return modelMapper.map(guardado, TrasladoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TrasladoResponseDTO obtenerPorId(Long id) {
        Traslado entity = trasladoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + id));
        return modelMapper.map(entity, TrasladoResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrasladoResponseDTO> listarTodos() {
        return trasladoRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TrasladoResponseDTO.class))
                .toList();
    }

    @Override
    public TrasladoResponseDTO actualizar(Long id, TrasladoRequestDTO request) {
        Traslado entity = trasladoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + id));

        // actualiza campos
        modelMapper.map(request, entity);

        Traslado actualizado = trasladoRepository.save(entity);
        return modelMapper.map(actualizado, TrasladoResponseDTO.class);
    }

    @Override
    public TrasladoResponseDTO cambiarEstado(Long id, String estado) {
        Traslado entity = trasladoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Traslado no encontrado con id: " + id));
        entity.setEstado(estado);
        return modelMapper.map(trasladoRepository.save(entity), TrasladoResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!trasladoRepository.existsById(id)) {
            throw new EntityNotFoundException("Traslado no encontrado con id: " + id);
        }
        trasladoRepository.deleteById(id);
    }
}
