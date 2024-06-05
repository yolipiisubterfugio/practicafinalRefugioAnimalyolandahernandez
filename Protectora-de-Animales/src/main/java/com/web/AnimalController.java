package com.protectora.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.protectora.data.AnimalRepository;
import com.protectora.model.Animal;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @GetMapping
    public Iterable<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Animal> getAnimalById(@PathVariable Long id) {
        return animalRepository.findById(id);
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(animalDetails.getName());
            animal.setType(animalDetails.getType());
            animal.setAge(animalDetails.getAge());
            animal.setDescription(animalDetails.getDescription());
            return animalRepository.save(animal);
        }).orElseGet(() -> {
            animalDetails.setId(id);
            return animalRepository.save(animalDetails);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}
