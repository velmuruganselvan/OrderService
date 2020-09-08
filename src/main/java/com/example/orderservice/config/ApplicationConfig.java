package com.example.orderservice.config;

import com.example.orderservice.util.OrderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    @Bean
    public OrderUtil orderUtil() { return  new OrderUtil(); }

    @Bean
    public ObjectMapper getObjectMapper() { return new ObjectMapper(); }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }


}
