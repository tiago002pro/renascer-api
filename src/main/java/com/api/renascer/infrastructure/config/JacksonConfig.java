package com.api.renascer.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))
                .build();

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setDateFormat((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));

        return mapper;
    }
}
