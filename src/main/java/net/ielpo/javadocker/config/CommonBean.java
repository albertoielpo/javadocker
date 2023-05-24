package net.ielpo.javadocker.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.ielpo.javadocker.model.Version;

/**
 * @author Alberto Ielpo
 */
@Configuration
public class CommonBean {

    @Bean
    public Version getVersion() {
        return new Version() {
            {
                this.name = "java-docker";
                this.uuid = UUID.randomUUID().toString();
            }
        };
    }

    @Bean
    ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
