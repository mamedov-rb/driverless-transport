package com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto;

import com.rb.alwaysontheroad.restqueuemetricsadapter.data.enums.Status;
import com.rb.alwaysontheroad.restqueuemetricsadapter.data.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TransportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2567653491060394677L;

    private UUID id;

    private String serialNumber;

    private String model;

    private Type type;

    private Status status;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
