package com.rb.alwaysontheroad.restqueuemetricsadapter.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.restqueuemetricsadapter.constants.ApplicationConstants;
import com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto.MetricsDto;
import com.rb.alwaysontheroad.restqueuemetricsadapter.producer.MetricsProducer;
import com.rb.alwaysontheroad.restqueuemetricsadapter.rest.validation.Created;
import com.rb.alwaysontheroad.restqueuemetricsadapter.rest.view.Views;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsProducer metricsProducer;

    @JsonView(value = {Views.Public.class})
    @PostMapping(
            value = ApplicationConstants.BASE_URL_V1,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MetricsDto> pass(@RequestBody @Validated({Created.class}) MetricsDto metricsDto) {
        metricsProducer.pass(metricsDto);
        return ResponseEntity.ok(metricsDto);
    }
}
