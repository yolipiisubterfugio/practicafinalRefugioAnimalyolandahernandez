package com.protectora.data;

import org.springframework.data.repository.CrudRepository;
import com.protectora.model.Adoption;

public interface AdoptionRepository extends CrudRepository<Adoption, Long> {
}
