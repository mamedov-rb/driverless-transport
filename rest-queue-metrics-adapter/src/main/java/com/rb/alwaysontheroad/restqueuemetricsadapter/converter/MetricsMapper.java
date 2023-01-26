package com.rb.alwaysontheroad.restqueuemetricsadapter.converter;

import com.rb.alwaysontheroad.restqueuemetricsadapter.data.dto.MetricsDto;
import com.rb.alwaysontheroad.restqueuemetricsadapter.data.message.MetricsMessage;
import org.mapstruct.Mapper;

@Mapper
public interface MetricsMapper {

    MetricsDto convert(MetricsMessage message);

    MetricsMessage convert(MetricsDto dto);
}
