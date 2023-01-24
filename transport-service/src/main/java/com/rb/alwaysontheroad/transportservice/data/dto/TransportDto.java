package com.rb.alwaysontheroad.transportservice.data.dto;

import com.rb.alwaysontheroad.transportservice.data.enums.Status;
import com.rb.alwaysontheroad.transportservice.data.enums.Type;
import com.rb.alwaysontheroad.transportservice.rest.validation.Created;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TransportDto {

    @Null(groups = {Created.class})
    private UUID id;

    @NotBlank(groups = {Created.class})
    private String serialNumber;

    @NotBlank(groups = {Created.class})
    private String model;

    @NotNull(groups = {Created.class})
    private Type type;

    @NotNull(groups = {Created.class})
    private Status status;

    @Null(groups = {Created.class})
    private Instant createdAt;

    @Null(groups = {Created.class})
    private Instant updatedAt;

    @Null(groups = {Created.class})
    private String createdBy;

    @Null(groups = {Created.class})
    private String updatedBy;
}
