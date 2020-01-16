package com.demo.elastic.search.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("demo_Gson")
public class GsonFactory extends AbstractFactoryBean<Gson> {

    @Override
    public Class<?> getObjectType() {
        return Gson.class;
    }

    @Override
    protected Gson createInstance() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new ElasticDocumentDateTypeAdapter());

        return gsonBuilder.create();
    }

    static class ElasticDocumentDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        public static final String DATE_PATTERN = "yyyy-MM-dd";

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern(DATE_PATTERN));
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}
