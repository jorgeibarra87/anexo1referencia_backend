package husj.referencia.service.impl;

import husj.referencia.model.dto.request.TramiteRequestDTO;
import husj.referencia.model.dto.response.IntraResumenDTO;
import husj.referencia.model.dto.response.TramiteCompletoResponseDTO;
import husj.referencia.model.dto.response.TramiteResponseDTO;
import husj.referencia.model.entity.*;
import husj.referencia.repository.PacienteRepository;
import husj.referencia.repository.TipoSolicitudCatalogoRepository;
import husj.referencia.repository.TramiteRepository;
import husj.referencia.service.TramiteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TramiteServiceImpl implements TramiteService {

    private final TramiteRepository tramiteRepository;
    private final PacienteRepository pacienteRepository;
    private final TipoSolicitudCatalogoRepository tipoSolicitudCatalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TramiteResponseDTO crear(TramiteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + request.getPacienteId()));

        Tramite entity = new Tramite();
        //entity.setNumeroTramite(request.getNumeroTramite());
        entity.setPaciente(paciente);
        entity.setIngreso(request.getIngreso());
        entity.setServicio(request.getServicio());
        entity.setDescripcion(request.getDescripcion());
        entity.setAuxiliarReferencia(request.getAuxiliarReferencia());
        if (request.getEstado() != null) {
            entity.setEstado(request.getEstado());
        }
        if (request.getTipoSolicitudId() != null) {
            TipoSolicitudCatalogo catalogo = tipoSolicitudCatalogoRepository.findById(request.getTipoSolicitudId())
                    .orElseThrow(() -> new EntityNotFoundException("TipoSolicitud no encontrado con id: " + request.getTipoSolicitudId()));
            entity.setTipoSolicitud(catalogo);
        }

        Tramite guardado = tramiteRepository.save(entity);
        return toResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public TramiteResponseDTO obtenerPorId(Long id) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));
        return toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TramiteResponseDTO> listarTodos() {
        return tramiteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TramiteCompletoResponseDTO> listarCompletos() {
        return tramiteRepository.findAll().stream()
                .map(this::toCompletoResponseDTO)
                .toList();
    }

    @Override
    public TramiteResponseDTO actualizar(Long id, TramiteRequestDTO request) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));

        if (request.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + request.getPacienteId()));
            entity.setPaciente(paciente);
        }
        if (request.getTipoSolicitudId() != null) {
            TipoSolicitudCatalogo catalogo = tipoSolicitudCatalogoRepository.findById(request.getTipoSolicitudId())
                    .orElseThrow(() -> new EntityNotFoundException("TipoSolicitud no encontrado con id: " + request.getTipoSolicitudId()));
            entity.setTipoSolicitud(catalogo);
        }

        modelMapper.map(request, entity);
        return toResponseDTO(tramiteRepository.save(entity));
    }

    @Override
    public TramiteResponseDTO cambiarEstado(Long id, String estado) {
        Tramite entity = tramiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tramite no encontrado con id: " + id));
        entity.setEstado(Tramite.Estado.valueOf(estado));
        return toResponseDTO(tramiteRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!tramiteRepository.existsById(id)) {
            throw new EntityNotFoundException("Tramite no encontrado con id: " + id);
        }
        tramiteRepository.deleteById(id);
    }

    private TramiteResponseDTO toResponseDTO(Tramite entity) {
        TramiteResponseDTO dto = modelMapper.map(entity, TramiteResponseDTO.class);
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setPacienteNombre(entity.getPaciente().getNombreCompleto());
        dto.setPacienteDocumento(entity.getPaciente().getNumeroDocumento());
        dto.setPacienteEps(entity.getPaciente().getEps());
        if (entity.getTipoSolicitud() != null) {
            dto.setTipoSolicitudId(entity.getTipoSolicitud().getId());
            dto.setTipoSolicitudDescripcion(entity.getTipoSolicitud().getDescripcion());
        }
        return dto;
    }

    private TramiteCompletoResponseDTO toCompletoResponseDTO(Tramite entity) {
        TramiteCompletoResponseDTO dto = modelMapper.map(entity, TramiteCompletoResponseDTO.class);
        dto.setPacienteNombre(entity.getPaciente().getNombreCompleto());
        dto.setPacienteDocumento(entity.getPaciente().getNumeroDocumento());
        dto.setPacienteEps(entity.getPaciente().getEps());
        dto.setEstado(entity.getEstado().name());
        if (entity.getTipoSolicitud() != null) {
            dto.setTipoSolicitudDescripcion(entity.getTipoSolicitud().getDescripcion());
        }
        if (entity.getEgreso() != null) {
            Egreso e = entity.getEgreso();
            dto.setEgresoServicio(e.getServicioEgreso());
            dto.setEgresoFecha(e.getFechaEgreso());
        }
        if (entity.getSeguimientosIntra() != null && !entity.getSeguimientosIntra().isEmpty()) {
            SeguimientoIntrahospitalario s = entity.getSeguimientosIntra().get(entity.getSeguimientosIntra().size() - 1);
            dto.setIntraFechaSeguimiento(s.getFechaSeguimiento());
            dto.setIntraAutorizacion(s.getAutorizacion());
            dto.setIntraEstadoAutorizacion(s.getEstadoAutorizacion().name());
            dto.setIntraAuxiliarReferencia(s.getAuxiliarReferencia());
            dto.setIntraSeguimientos(entity.getSeguimientosIntra().stream()
                    .map(si -> IntraResumenDTO.builder()
                            .fechaSeguimiento(si.getFechaSeguimiento())
                            .autorizacion(si.getAutorizacion())
                            .estadoAutorizacion(si.getEstadoAutorizacion().name())
                            .auxiliarReferencia(si.getAuxiliarReferencia())
                            .build())
                    .toList());
        }
        if (entity.getSeguimientosAmbulatorios() != null && !entity.getSeguimientosAmbulatorios().isEmpty()) {
            SeguimientoAmbulatorio s = entity.getSeguimientosAmbulatorios().get(entity.getSeguimientosAmbulatorios().size() - 1);
            dto.setAmbulatorioNotaSeguimiento(s.getNotaSeguimiento());
            dto.setAmbulatorioFechaNota(s.getFechaNota());
        }
        return dto;
    }
}
