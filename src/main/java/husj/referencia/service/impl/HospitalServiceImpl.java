package husj.referencia.service.impl;

import husj.referencia.model.dto.request.HospitalReqDTO;
import husj.referencia.model.dto.response.HospitalResDTO;
import husj.referencia.model.entity.Hospital;
import husj.referencia.repository.HospitalRepository;
import husj.referencia.service.HospitalService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<HospitalResDTO> ListarHospital() {
        return hospitalRepository.findAll()
                .stream().map(hospital-> modelMapper.map(hospital, HospitalResDTO.class))
                .toList();
    }

    @Override
    public Optional<HospitalResDTO> buscarPorId(Long id) {
        return hospitalRepository.findById(id)
                .map(hospital -> modelMapper.map(hospital, HospitalResDTO.class));
    }

    @Override
    public HospitalReqDTO guardar(HospitalReqDTO dto) {
        Hospital entidad = modelMapper.map(dto, Hospital.class);

        Hospital guardado = hospitalRepository.save(entidad);
        return modelMapper.map(guardado, HospitalReqDTO.class);
    }

}
