package com.rb.alwaysontheroad.rawmetricsconsumer.service;

import com.rb.alwaysontheroad.rawmetricsconsumer.constants.ApplicationConstants;
import com.rb.alwaysontheroad.rawmetricsconsumer.data.message.MetricsMessage;
import com.rb.alwaysontheroad.rawmetricsconsumer.data.model.Metrics;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsService {

    private final InfluxDBTemplate<Point> influxDBTemplate;

    @KafkaListener(id = "${spring.kafka.consumer.client-id}", topics = "${spring.kafka.template.default-topic}")
    public void save(MetricsMessage metricsMessage) {
        log.debug("Received raw-metrics message: '{}'", metricsMessage);
        Point point = Point.measurement(ApplicationConstants.METRICS_TABLE)
                .time(Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS)
                .addField(Metrics.Fields.transportId, metricsMessage.getTransportId().toString())
                .addField(Metrics.Fields.payload, metricsMessage.getPayload())
                .build();

        influxDBTemplate.write(point);
    }

    @PostConstruct
    public void init() {
        influxDBTemplate.createDatabase();
    }
}
