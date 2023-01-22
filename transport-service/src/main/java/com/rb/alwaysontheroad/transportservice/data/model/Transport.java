package com.rb.alwaysontheroad.transportservice.data.model;

import com.rb.alwaysontheroad.transportservice.data.enums.Status;
import com.rb.alwaysontheroad.transportservice.data.enums.Type;
import com.rb.alwaysontheroad.transportservice.service.TransportListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@EntityListeners({TransportListener.class})
@FieldNameConstants
public class Transport {

    @Id
    @GeneratedValue(generator = "transportIdGenerator")
    @GenericGenerator(name = "transportIdGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String serialNumber;

    private String model;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
