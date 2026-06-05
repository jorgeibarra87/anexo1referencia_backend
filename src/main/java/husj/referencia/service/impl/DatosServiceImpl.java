package husj.referencia.service.impl;

import husj.referencia.model.dto.request.DatosIngresoUpdateReqDTO;
import husj.referencia.model.dto.request.DatosReqDTO;
import husj.referencia.model.dto.request.IngresoReqDTO;
import husj.referencia.model.dto.response.DatosResDTO;
import husj.referencia.model.entity.Datos;
import husj.referencia.model.entity.Ingreso;
import husj.referencia.repository.DatosRepository;
import husj.referencia.repository.HospitalRepository;
import husj.referencia.service.DatosService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DatosServiceImpl implements DatosService {

    private final DatosRepository datosRepository;
    private final HospitalRepository hospitalRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<DatosResDTO> listarDatos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        // fecha de corte con la que decidimos si consultamos ingresos posteriores o no
        LocalDate fechaCorte = LocalDate.now().minusDays(5);

        return datosRepository.findAllByOrderByIdDesc(pageable)
                .map(datos -> {
                    DatosResDTO dto = modelMapper.map(datos, DatosResDTO.class);
                    dto.setEsAntiguo(datos.getFechaComentario() != null && datos.getFechaComentario().isBefore(fechaCorte));
                    return dto;
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DatosResDTO> listarDatosPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Datos> datos = datosRepository.findByFechaBetween(fechaInicio, fechaFin);
        return datos.stream()
                .map(dato -> modelMapper.map(dato, DatosResDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DatosResDTO guardar(DatosReqDTO dto) {
        Datos entidad = modelMapper.map(dto, Datos.class);
        // setear relaciones
        if (dto.getHospital_id() != null) {
            entidad.setHospital(
                    hospitalRepository.findById(dto.getHospital_id()).orElseThrow(() -> new RuntimeException("Hospital no encontrado"))
            );
        }
        if(entidad.getIngresos() != null){
            for(Ingreso ingreso : entidad.getIngresos()){
                ingreso.setDatos(entidad);
            }
        }

        Datos guardado = datosRepository.save(entidad);
        return modelMapper.map(guardado, DatosResDTO.class);
    }

    @Override
    public void actualizarFechasRegistroParaBuscarIngresos(List<Long> ids) {
        List<Datos> datosList = datosRepository.findAllById(ids);
        LocalDateTime ahora = LocalDateTime.now();
        datosList.forEach(datos -> datos.setFechaActualizacionBusquedaIngresos(ahora));
        datosRepository.saveAll(datosList);
    }

    @Override
    public List<DatosResDTO> actualizarIngresos(List<DatosIngresoUpdateReqDTO> datos) {

        List<Long> ids = datos.stream().map(DatosIngresoUpdateReqDTO::getId).toList();
        Map<Long, List<IngresoReqDTO>> updatesMap = datos.stream()
                .collect(Collectors.toMap(
                        DatosIngresoUpdateReqDTO::getId,
                        DatosIngresoUpdateReqDTO::getIngresos
                ));

        List<Datos> datosLote = datosRepository.findAllByIdWithIngresos(ids);

        for(Datos dato : datosLote){
            List<IngresoReqDTO> ingresosNuevosDto = updatesMap.get(dato.getId());
            // Mappear los DTOs
            List<Ingreso> nuevosIngresos = ingresosNuevosDto.stream().map(d -> modelMapper.map(d, Ingreso.class)).toList();
            Set<LocalDate> fechasExistentes = dato.getIngresos().stream()
                    .map(Ingreso::getFechaIngreso)
                    .collect(Collectors.toSet());

            for(Ingreso nuevoIngreso : nuevosIngresos){
                if(!fechasExistentes.contains(nuevoIngreso.getFechaIngreso())){
                    nuevoIngreso.setDatos(dato);
                    dato.getIngresos().add(nuevoIngreso);
                }
            }
        }
        datosRepository.saveAll(datosLote);
        return datosLote.stream().map(m -> modelMapper.map(m, DatosResDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DatosResDTO actualizarComentarioTriage(Long id, String comentario) {
        Datos datosOpt = datosRepository.findById(id).orElseThrow(() -> new RuntimeException("Dato no encontrado"));
        datosOpt.setObservacionTriage(comentario);
        return modelMapper.map(datosRepository.save(datosOpt), DatosResDTO.class);
    }

    @Override
    public List<DatosResDTO> buscarPorIdODocumento(String valor) {
        try {
            Long idCandidato = Long.parseLong(valor);
            Optional<Datos> datosOpt = datosRepository.findById(idCandidato);
            if (datosOpt.isPresent()) {
                return List.of(modelMapper.map(datosOpt.get(), DatosResDTO.class));
            }
        } catch (NumberFormatException e) {
            // No hacemos nada con la excepción. Continuamos al Fallback.
        } catch (Exception e) {
            // Dejamos el catch para otros errores inesperados.
        }
        List<Datos> datosList = datosRepository.findByIdentificacion(valor);
        if (datosList.isEmpty()) {
            return Collections.emptyList();
        }
        return datosList.stream()
                .map(dato -> modelMapper.map(dato, DatosResDTO.class))
                .collect(Collectors.toList());
    }

}
