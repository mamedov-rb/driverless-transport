package com.rb.alwaysontheroad.transportservice.converter;

import com.rb.alwaysontheroad.transportservice.data.dto.TransportDto;
import com.rb.alwaysontheroad.transportservice.data.model.Transport;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;

@Mapper
public interface TransportMapper {

    TransportDto covert(@NotNull Transport transport);

    Transport covert(@NotNull TransportDto transportDto);
}
