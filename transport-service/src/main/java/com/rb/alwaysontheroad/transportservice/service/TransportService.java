package com.rb.alwaysontheroad.transportservice.service;

import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import com.rb.alwaysontheroad.transportservice.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRepository repository;

    @NotNull
    @Transactional
    public Transport create(@NotNull Transport transport) {
        return repository.save(transport);
    }

    @Transactional(readOnly = true)
    public Page<Transport> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
