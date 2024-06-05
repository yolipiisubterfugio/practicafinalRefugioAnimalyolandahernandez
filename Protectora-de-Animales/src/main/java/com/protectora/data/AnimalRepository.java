
package com.protectora.data;

import org.springframework.data.repository.CrudRepository;
import com.protectora.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
