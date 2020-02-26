package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.MarkDTO;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class CarAdapter implements CollateralObject {

    private CarDto car;

    public CarAdapter(CarDto car) {
        this.car = car;
    }

    @Override
    public BigDecimal getValue() {
        Optional<MarkDTO> value = car.getValues().stream().max(Comparator.comparing(MarkDTO::getCreateDateTime));
        return value.map(MarkDTO::getValue).orElse(null);
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public LocalDate getDate() {
        // Для автомобилей дата оценки не используется, поэтому всегда берем текущую
        return LocalDate.now();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
