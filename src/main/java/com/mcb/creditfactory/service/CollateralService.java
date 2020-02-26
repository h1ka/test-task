package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.EntityModel;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CollateralService {

    private CarService carService;

    private AirplaneService airplaneService;

    public Long saveCollateral(Collateral dto) {
        EntityService<EntityModel, Collateral> service = getService(dto);
        boolean approve = service.approve(dto);
        if (!approve) return null;
        EntityModel save = service.save(service.fromDto(dto));
        return save.getId();
//        return service.getId(save);
    }

    public Collateral getInfo(Collateral dto) {
        EntityService<EntityModel, Collateral> service = getService(dto);
        Long id = service.getId(service.fromDto(dto));
        Optional<EntityModel> load = service.load(id);
        return load.map(service::toDTO).orElse(null);
    }

    private EntityService getService(Collateral object) {
        Class<? extends Collateral> aClass = object.getClass();

        if (aClass.equals(CarDto.class)) {
            return carService;
        } else if (aClass.equals(AirplaneDto.class)) {
            return airplaneService;
        } else {
            throw new UnsupportedOperationException("Service for " + aClass.getSimpleName() + " is not implemented");
        }

    }

}
