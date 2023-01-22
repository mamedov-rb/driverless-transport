package com.rb.alwaysontheroad.transportservice.service;

import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransportListener {

    @PrePersist
    public void prePersist(Transport transport) {
        log.debug("Set 'createdBy' field");
    }

    @PreUpdate
    public void preUpdate(Transport transport) {
        log.debug("Set 'updatedBy' field");
    }
}
