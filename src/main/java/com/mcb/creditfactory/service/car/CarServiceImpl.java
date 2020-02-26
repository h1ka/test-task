package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.MarkDTO;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.Mark;
import com.mcb.creditfactory.repository.CarRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarServiceImpl implements CarService {

    private ExternalApproveService approveService;

    private CarRepository carRepository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        return Car.builder()
                .brand(dto.getBrand())
                .id(dto.getId())
                .model(dto.getModel())
                .power(dto.getPower())
                .marks(dto.getValues().stream()
                        .map(v -> Mark.builder()
                                .createDateTime(v.getCreateDateTime())
                                .value(v.getValue()).build()
                        ).collect(Collectors.toList()))
                .year(dto.getYear())
                .build();
    }

    @Override
    public CarDto toDTO(Car car) {
        return CarDto.builder()
                .brand(car.getBrand())
                .id(car.getId())
                .model(car.getModel())
                .power(car.getPower())
                .values(car.getMarks().stream().map(c -> MarkDTO.builder().createDateTime(c.getCreateDateTime()).value(c.getValue()).build()).collect(Collectors.toList()))
                .year(car.getYear())
                .build();
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }
}
