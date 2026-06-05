package husj.referencia.service.impl;

import husj.referencia.model.dto.request.IngresoReqDTO;
import husj.referencia.repository.IngresoRepository;
import husj.referencia.service.IngresoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository ingresoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<IngresoReqDTO> ListarIngreso(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ingresoRepository.findAll(pageable)
                .map(ingreso -> modelMapper.map(ingreso, IngresoReqDTO.class));
    }

    @Override
    public Optional<IngresoReqDTO> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public IngresoReqDTO guardar(IngresoReqDTO ingreso) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
