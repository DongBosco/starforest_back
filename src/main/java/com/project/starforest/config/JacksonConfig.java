package com.project.starforest.config;

import org.locationtech.jts.geom.Point;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {
	@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

//        Hibernate5Module hibernateModule = new Hibernate5Module();
//        hibernateModule.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
//        hibernateModule.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
//        mapper.registerModule(hibernateModule);
//        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
//                .featuresToDisable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
//                .build();
        SimpleModule module = new SimpleModule();

        module.addSerializer(Point.class, new PointSerializer());
        module.addDeserializer(Point.class, new PointDeserializer());
        mapper.registerModule(module);
        
        return mapper;
    }
}
