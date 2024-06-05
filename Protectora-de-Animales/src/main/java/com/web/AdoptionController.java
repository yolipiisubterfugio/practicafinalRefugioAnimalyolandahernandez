package com.protectora.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.protectora.data.AdoptionRepository;
import com.protectora.model.Adoption;

@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {
    
    @Autowired
    private AdoptionRepository adoptionRepository;
    
    @GetMapping
    public Iterable<Adoption> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Adoption> getAdoptionById(@PathVariable Long id) {
        return adoptionRepository.findById(id);
    }

    @PostMapping
    public Adoption createAdoption(@RequestBody Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @PutMapping("/{id}")
    public Adoption updateAdoption(@PathVariable Long id, @RequestBody Adoption adoptionDetails) {
        return adoptionRepository.findById(id).map(adoption -> {
            adoption.setAdopterName(adoptionDetails.getAdopterName());
            adoption.setAdopterAddress(adoptionDetails.getAdopterAddress());
            adoption.setAdopterPhone(adoptionDetails.getAdopterPhone());
            adoption.setAnimalId(adoptionDetails.getAnimalId());
            return adoptionRepository.save(adoption);
        }).orElseGet(() -> {
            adoptionDetails.setId(id);
            return adoptionRepository.save(adoptionDetails);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteAdoption(@PathVariable Long id) {
        adoptionRepository.deleteById(id);
    }
}
