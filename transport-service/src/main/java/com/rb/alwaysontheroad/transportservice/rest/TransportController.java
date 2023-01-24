package com.rb.alwaysontheroad.transportservice.rest;

import com.rb.alwaysontheroad.transportservice.constants.ApplicationConstants;
import com.rb.alwaysontheroad.transportservice.converter.TransportMapper;
import com.rb.alwaysontheroad.transportservice.data.dto.TransportDto;
import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import com.rb.alwaysontheroad.transportservice.rest.validation.Created;
import com.rb.alwaysontheroad.transportservice.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportController {

    private final TransportMapper transportMapper;
    private final TransportService transportService;

    @PostMapping(
            value = ApplicationConstants.BASE_URL_V1,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransportDto> create(@RequestBody @Validated({Created.class}) TransportDto transportDto) {
        Transport transport = transportMapper.covert(transportDto);
        Transport savedTransport = transportService.create(transport);
        TransportDto savedTransportDto = transportMapper.covert(savedTransport);
        return ResponseEntity.ok(savedTransportDto);
    }
}
