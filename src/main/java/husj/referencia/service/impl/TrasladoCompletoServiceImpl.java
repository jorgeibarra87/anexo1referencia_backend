package husj.referencia.service.impl;

import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;
import husj.referencia.model.dto.response.TrasladoCompletoResponseDTO;
import husj.referencia.model.dto.response.TrasladoResponseDTO;
import husj.referencia.repository.CuentaMedicaRepository;
import husj.referencia.repository.FacturacionRepository;
import husj.referencia.repository.TrasladoRepository;
import husj.referencia.service.TrasladoCompletoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrasladoCompletoServiceImpl implements TrasladoCompletoService {

    private final TrasladoRepository trasladoRepository;
    private final FacturacionRepository facturacionRepository;
    private final CuentaMedicaRepository cuentaMedicaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TrasladoCompletoResponseDTO> listarTodos() {
        return trasladoRepository.findAll().stream()
                .map(traslado -> TrasladoCompletoResponseDTO.builder()
                        .traslado(modelMapper.map(traslado, TrasladoResponseDTO.class))
                        .facturaciones(
                                facturacionRepository.findByTrasladoId(traslado.getId()).stream()
                                        .map(f -> modelMapper.map(f, FacturacionResponseDTO.class))
                                        .toList()
                        )
                        .cuentasMedicas(
                                cuentaMedicaRepository.findByTrasladoId(traslado.getId()).stream()
                                        .map(c -> modelMapper.map(c, CuentaMedicaResponseDTO.class))
                                        .toList()
                        )
                        .build()
                )
                .toList();
    }
}

