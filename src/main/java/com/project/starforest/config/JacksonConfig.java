package com.project.starforest.config;

import com.fasterxml.jackson.databind.MapperFeature;
import org.locationtech.jts.geom.Point;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
	@Bean
    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
////        mapper.registerModule(new JavaTimeModule());

        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
                .build();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(Point.class, new PointSerializer());
//        module.addDeserializer(Point.class, new PointDeserializer());
//        mapper.registerModule(module);
        
        return mapper;
    }
}
