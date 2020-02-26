package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.MarkDTO;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class AirplaneAdapter implements CollateralObject {

    private AirplaneDto dto;

    public AirplaneAdapter(AirplaneDto dto) {
        this.dto = dto;
    }

    @Override
    public BigDecimal getValue() {
        Optional<MarkDTO> value = dto.getValues().stream().max(Comparator.comparing(MarkDTO::getCreateDateTime));
        return value.map(MarkDTO::getValue).orElse(null);
    }

    @Override
    public Short getYear() {
        return dto.getYear();
    }

    @Override
    public LocalDate getDate() {
        Optional<MarkDTO> value = dto.getValues().stream().max(Comparator.comparing(MarkDTO::getCreateDateTime));
        return value.map(MarkDTO::getCreateDateTime).map(LocalDateTime::toLocalDate).orElse(null);
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
