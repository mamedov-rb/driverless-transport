package com.rb.alwaysontheroad.restqueuemetricsadapter.data.message;

import com.rb.alwaysontheroad.restqueuemetricsadapter.rest.validation.Created;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class MetricsMessage {

    @NotNull(groups = {Created.class})
    private UUID transport_id;

    @NotBlank(groups = {Created.class})
    private String payload;
}
