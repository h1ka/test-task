package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.MarkDTO;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Mark;
import com.mcb.creditfactory.repository.AirplaneRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AirplaneServiceImpl implements AirplaneService {

    private ExternalApproveService approveService;

    private AirplaneRepository repository;

    @Override
    public boolean approve(AirplaneDto airplaneDto) {
        return approveService.approve(new AirplaneAdapter(airplaneDto)) == 0;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return repository.save(airplane);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return repository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        return Airplane.builder()
                .brand(dto.getBrand())
                .id(dto.getId())
                .model(dto.getModel())
                .year(dto.getYear())
                .fuelCapacity(dto.getFuelCapacity())
                .manufacturer(dto.getManufacturer())
                .seats(dto.getSeats())
                .marks(dto.getValues().stream()
                        .map(v -> Mark.builder()
                                .createDateTime(v.getCreateDateTime())
                        .value(v.getValue()).build()
                ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AirplaneDto toDTO(Airplane airplane) {
        return AirplaneDto.builder()
                .brand(airplane.getBrand())
                .id(airplane.getId())
                .model(airplane.getModel())
                .year(airplane.getYear())
                .fuelCapacity(airplane.getFuelCapacity())
                .manufacturer(airplane.getManufacturer())
                .values(airplane.getMarks().stream().map(v -> MarkDTO.builder().value(v.getValue()).createDateTime(v.getCreateDateTime()).build()).collect(Collectors.toList()))
                .seats(airplane.getSeats())
                .build();
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }
}
