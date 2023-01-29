package com.rb.alwaysontheroad.rawmetricsconsumer.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private Instant time;
}
