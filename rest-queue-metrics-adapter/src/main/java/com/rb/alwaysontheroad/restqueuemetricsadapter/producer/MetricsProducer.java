package com.rb.alwaysontheroad.restqueuemetricsadapter.producer;

import com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto.MetricsDto;
import com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto.TransportDto;
import com.rb.alwaysontheroad.restqueuemetricsadapter.exception.AccessDeniedException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetricsProducer {

    private final KafkaTemplate<String, MetricsDto> kafkaTemplate;
    private final RedisTemplate<String, TransportDto> redisTemplate;

    @NotNull
    public void pass(@NotNull MetricsDto metricsDto) {
        Boolean isVerifiedUnit = redisTemplate.opsForValue()
                .getOperations()
                .hasKey(metricsDto.getTransportId().toString());

        if (Boolean.FALSE.equals(isVerifiedUnit)) {
            throw new AccessDeniedException(String.format(
                    "Transport with id: '%s' is not verified or application cache has lost it.",
                    metricsDto.getTransportId()
            ));
        }

        CompletableFuture<SendResult<String, MetricsDto>> completableFuture =
                kafkaTemplate.send(MessageBuilder.withPayload(metricsDto).build());

        completableFuture.whenComplete((result, ex) -> {
            if (!Objects.nonNull(ex)) {
                log.debug("Successfully send message: '{}'", result.getProducerRecord().value());
            } else {
                log.error("Failed to send message", ex);
            }
        });
    }
}
