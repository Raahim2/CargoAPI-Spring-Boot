package com.example.cargapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CargoAPI {

    private final LoadRepo loadRepo;

    @Autowired
    public CargoAPI(LoadRepo loadRepo) {
        this.loadRepo = loadRepo;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Cargo API";
    }

 
    @PostMapping("/load")
    public ResponseEntity<Load> createLoad(@RequestBody Load newLoad) {
        Load savedLoad = loadRepo.save(newLoad);
        return new ResponseEntity<>(savedLoad, HttpStatus.CREATED);
    }

    @GetMapping("/load")
    public ResponseEntity<Page<Load>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType,
            @RequestParam(required = false) Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Load filterBy = new Load();
        filterBy.setShipperId(shipperId);
        filterBy.setTruckType(truckType);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Load> example = Example.of(filterBy, matcher);
        Pageable pageable = PageRequest.of(page, size);

        Page<Load> loads = loadRepo.findAll(example, pageable);
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/load/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId) {
        return loadRepo.findById(loadId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  
    @PutMapping("/load/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoadDetails) {
        return loadRepo.findById(loadId)
                .map(existingLoad -> {
                    existingLoad.setFacility(updatedLoadDetails.getFacility());
                    existingLoad.setProductType(updatedLoadDetails.getProductType());
                    existingLoad.setTruckType(updatedLoadDetails.getTruckType());
                    existingLoad.setNoOfTrucks(updatedLoadDetails.getNoOfTrucks());
                    existingLoad.setWeight(updatedLoadDetails.getWeight());
                    existingLoad.setComment(updatedLoadDetails.getComment());
                    existingLoad.setStatus(updatedLoadDetails.getStatus());

                    Load savedLoad = loadRepo.save(existingLoad);
                    return ResponseEntity.ok(savedLoad);
                })
                .orElse(ResponseEntity.notFound().build());
    }

  
    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        if (loadRepo.existsById(loadId)) {
            loadRepo.deleteById(loadId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

