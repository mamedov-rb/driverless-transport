package com.rb.alwaysontheroad.transportservice.service;

import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransportListener {

    @PrePersist
    public void prePersist(Transport transport) {
        transport.setCreatedBy("mocked-user");// TODO: 24.01.2023 Add real user from JWT token.
        log.debug("Set '{}' field, value: '{}'", Transport.Fields.createdBy, transport.getCreatedBy());
    }

    @PreUpdate
    public void preUpdate(Transport transport) {
        transport.setUpdatedBy("mocked-user");// TODO: 24.01.2023 Add real user from JWT token.
        log.debug("Set '{}' field, value: '{}'", Transport.Fields.updatedBy, transport.getUpdatedBy());
    }
}
