package husj.referencia.config;

import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;
import husj.referencia.model.entity.CuentaMedica;
import husj.referencia.model.entity.Facturacion;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // Configuración básica sin mapeos personalizados que causan conflictos
        ModelMapper mapper = new ModelMapper();

        // Configuración simple y segura
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        mapper.typeMap(Facturacion.class, FacturacionResponseDTO.class)
                .addMappings(m -> {
                    m.map(src -> src.getTraslado().getDocumento(),   FacturacionResponseDTO::setDocumento);
                    m.map(src -> src.getTraslado().getNomPaciente(), FacturacionResponseDTO::setNomPaciente);
                    m.map(src -> src.getTraslado().getIngreso(), FacturacionResponseDTO::setIngreso);

                });

        mapper.typeMap(CuentaMedica.class, CuentaMedicaResponseDTO.class)
                .addMappings(m -> {
                    m.map(src -> src.getTraslado().getDocumento(),   CuentaMedicaResponseDTO::setDocumento);
                    m.map(src -> src.getTraslado().getNomPaciente(), CuentaMedicaResponseDTO::setNomPaciente);
                    m.map(src -> src.getTraslado().getIngreso(), CuentaMedicaResponseDTO::setIngreso);
                });

        return mapper;
    }
}
