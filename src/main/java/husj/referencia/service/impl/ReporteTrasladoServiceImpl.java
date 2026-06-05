package husj.referencia.service.impl;

import husj.referencia.model.dto.response.ReporteTrasladoResponseDTO;
import husj.referencia.model.entity.CuentaMedica;
import husj.referencia.model.entity.Facturacion;
import husj.referencia.model.entity.Traslado;
import husj.referencia.repository.TrasladoRepository;
import husj.referencia.repository.FacturacionRepository;
import husj.referencia.repository.CuentaMedicaRepository;
import husj.referencia.service.ReporteTrasladoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReporteTrasladoServiceImpl implements ReporteTrasladoService {

    private final TrasladoRepository trasladoRepository;
    private  final FacturacionRepository facturacionRepository;
    private final CuentaMedicaRepository cuentaMedicaRepository;

    @Override
    public List<ReporteTrasladoResponseDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.atTime(LocalTime.MAX);

        List<Traslado> traslados = trasladoRepository.findByFechaTrasladoBetween(inicio, fin);

        return traslados.stream()
                .map(this::mapearReporte)
                .toList();
    }

    private ReporteTrasladoResponseDTO mapearReporte(Traslado traslado) {
        Facturacion facturacion = facturacionRepository.findByTrasladoIdOrderByFechaFacturaDesc(traslado.getId())
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        CuentaMedica cuenta = cuentaMedicaRepository.findByTrasladoIdOrderByFechaCuentaDesc(traslado.getId())
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        return ReporteTrasladoResponseDTO.builder()
                .idTraslado(traslado.getId())
                .fechaTraslado(traslado.getFechaTraslado())
                .nomPaciente(traslado.getNomPaciente())
                .documento(traslado.getDocumento())
                .ingreso(traslado.getIngreso())
                .eps(traslado.getEps())
                .tipoTraslado(traslado.getTipoTraslado())
                .servicio(traslado.getServicio())
                .destino(traslado.getDestino())
                .ciudad(traslado.getCiudad())
                .autorizacion(traslado.getAutorizacion())
                .auxiliarReferencia(traslado.getAuxiliarReferencia())
                .auxiliarAmbulancia(traslado.getAuxiliarAmbulancia())
                .medicamentos(traslado.getMedicamentos())
                .archivo(traslado.getArchivo())
                .observacionTraslado(traslado.getObservaciones())
                .estadoTraslado(traslado.getEstado())

                .produccion(facturacion != null ? facturacion.getProduccion() : null)
                .fechaFactura(facturacion != null ? facturacion.getFechaFactura() : null)
                .factura(facturacion != null ? facturacion.getFactura() : null)
                .valor(facturacion != null ? facturacion.getValor() : null)
                .nombreFacturador(facturacion != null ? facturacion.getNombreFacturador() : null)
                .estadoFacturacion(facturacion != null ? facturacion.getEstado() : null)

                .fechaCuenta(cuenta != null ? cuenta.getFechaCuenta() : null)
                .servicioEgreso(cuenta != null ? cuenta.getServicioEgreso() : null)
                .responsableAuditoria(cuenta != null ? cuenta.getResponsableAuditoria() : null)
                .observacionCuentas(cuenta != null ? cuenta.getObservaciones() : null)
                .estadoCuenta(cuenta != null ? cuenta.getEstado() : null)
                .fechaEgreso(cuenta != null ? cuenta.getFechaEgreso() : null)
                .build();
    }
}