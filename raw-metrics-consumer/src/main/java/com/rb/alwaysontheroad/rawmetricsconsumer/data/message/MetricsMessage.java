package com.rb.alwaysontheroad.rawmetricsconsumer.data.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class MetricsMessage {

    private UUID transportId;

    private String payload;
}
