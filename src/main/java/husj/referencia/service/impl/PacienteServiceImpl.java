package husj.referencia.service.impl;

import husj.referencia.model.dto.request.PacienteRequestDTO;
import husj.referencia.model.dto.response.PacienteResponseDTO;
import husj.referencia.model.entity.Paciente;
import husj.referencia.repository.PacienteRepository;
import husj.referencia.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PacienteResponseDTO crear(PacienteRequestDTO request) {
        Paciente entity = modelMapper.map(request, Paciente.class);
        Paciente guardado = pacienteRepository.save(entity);
        return modelMapper.map(guardado, PacienteResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponseDTO obtenerPorId(Long id) {
        Paciente entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        return modelMapper.map(entity, PacienteResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, PacienteResponseDTO.class))
                .toList();
    }

    @Override
    public PacienteResponseDTO actualizar(Long id, PacienteRequestDTO request) {
        Paciente entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        modelMapper.map(request, entity);
        return modelMapper.map(pacienteRepository.save(entity), PacienteResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente no encontrado con id: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
