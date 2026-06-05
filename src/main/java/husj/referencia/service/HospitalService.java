package husj.referencia.service;

import husj.referencia.model.dto.request.HospitalReqDTO;
import husj.referencia.model.dto.response.HospitalResDTO;

import java.util.List;
import java.util.Optional;

public interface HospitalService {

    List<HospitalResDTO> ListarHospital();
    Optional<HospitalResDTO> buscarPorId(Long id);
    HospitalReqDTO guardar(HospitalReqDTO hospital);

}
