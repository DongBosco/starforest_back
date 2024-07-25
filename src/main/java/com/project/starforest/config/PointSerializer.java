package com.project.starforest.config;

import java.io.IOException;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PointSerializer extends JsonSerializer<Point>{

	@Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (point != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("latitude", point.getY());
            jsonGenerator.writeNumberField("longitude", point.getX());
            jsonGenerator.writeEndObject();
        }
    }
	
}
