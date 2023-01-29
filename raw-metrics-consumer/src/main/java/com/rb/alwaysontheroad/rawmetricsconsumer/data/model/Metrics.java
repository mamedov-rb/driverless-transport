package com.rb.alwaysontheroad.rawmetricsconsumer.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@Setter
@ToString
@FieldNameConstants
public class Metrics {

    private UUID transportId;

    private String payload;
}
