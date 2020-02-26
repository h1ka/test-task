package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Mark;
import org.springframework.data.repository.CrudRepository;

public interface ValueRepository extends CrudRepository<Mark, Long> {
}
