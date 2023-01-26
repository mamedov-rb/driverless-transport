package com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.rb.alwaysontheroad.restqueuemetricsadapter.rest.validation.Created;
import com.rb.alwaysontheroad.restqueuemetricsadapter.rest.view.Views;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class MetricsDto {

    @NotNull(groups = {Created.class})
    @JsonView(value = {Views.Public.class})
    private UUID transport_id;

    @NotBlank(groups = {Created.class})
    private String payload;
}
