package com.rb.alwaysontheroad.transportservice.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.transportservice.constants.ApplicationConstants;
import com.rb.alwaysontheroad.transportservice.converter.TransportMapper;
import com.rb.alwaysontheroad.transportservice.data.dto.TransportDto;
import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import com.rb.alwaysontheroad.transportservice.rest.validation.Created;
import com.rb.alwaysontheroad.transportservice.rest.view.Views;
import com.rb.alwaysontheroad.transportservice.service.TransportService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransportController {

    private final TransportMapper transportMapper;
    private final TransportService transportService;
    private final RedisTemplate<String, TransportDto> redisTemplate;

    @JsonView(value = {Views.Public.class})
    @PostMapping(
            value = ApplicationConstants.BASE_URL_V1,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransportDto> create(@RequestBody @Validated({Created.class}) TransportDto transportDto) {
        Transport transport = transportMapper.covert(transportDto);
        Transport savedTransport = transportService.create(transport);
        TransportDto savedTransportDto = transportMapper.covert(savedTransport);

        redisTemplate.opsForValue()
                .set(savedTransportDto.getId().toString(), savedTransportDto);
        return ResponseEntity.ok(savedTransportDto);
    }

    @PostConstruct
    public void initCache() {
        Page<Transport> transports = transportService.findAll(Pageable.unpaged());
        log.debug("Warming up verified-transport cache with: '{}' items", transports.getSize());

        transports.stream()
                .map(transportMapper::covert)
                .forEach(transportDto -> redisTemplate.opsForValue()
                        .set(transportDto.getId().toString(), transportDto)
                );
    }
}
