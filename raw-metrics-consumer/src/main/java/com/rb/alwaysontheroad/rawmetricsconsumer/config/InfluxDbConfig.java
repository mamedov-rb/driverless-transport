package com.rb.alwaysontheroad.rawmetricsconsumer.config;

import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDbConfig {

    @Bean
    public InfluxDBConnectionFactory connectionFactory(InfluxDBProperties properties) {
        return new InfluxDBConnectionFactory(properties);
    }

    @Bean
    public InfluxDBTemplate<Point> influxDBTemplate(InfluxDBConnectionFactory connectionFactory) {
        return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
    }
}
