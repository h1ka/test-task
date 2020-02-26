package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.CollateralService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/collateral")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CollateralObjectController {

    private CollateralService service;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Collateral object) {
        Long id = service.saveCollateral(object);
        return Objects.nonNull(id) ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/info")
    public ResponseEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = service.getInfo(object);
        return Objects.nonNull(info) ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }
}
