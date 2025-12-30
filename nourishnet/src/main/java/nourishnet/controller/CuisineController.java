package nourishnet.controller;

import nourishnet.entity.Cuisine;
import nourishnet.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cuisines")
@CrossOrigin(origins = "http://localhost:3000")
public class CuisineController {
    
    @Autowired
    private CuisineService cuisineService;
    
    @GetMapping
    public ResponseEntity<List<Cuisine>> getAllCuisines() {
        List<Cuisine> cuisines = cuisineService.getAllCuisines();
        return ResponseEntity.ok(cuisines);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> getCuisineById(@PathVariable Long id) {
        return cuisineService.getCuisineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Cuisine> createCuisine(@Valid @RequestBody Cuisine cuisine) {
        Cuisine createdCuisine = cuisineService.createCuisine(cuisine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCuisine);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> updateCuisine(@PathVariable Long id, @Valid @RequestBody Cuisine cuisine) {
        try {
            Cuisine updatedCuisine = cuisineService.updateCuisine(id, cuisine);
            return ResponseEntity.ok(updatedCuisine);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuisine(@PathVariable Long id) {
        cuisineService.deleteCuisine(id);
        return ResponseEntity.noContent().build();
    }
}
