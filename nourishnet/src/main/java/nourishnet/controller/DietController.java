package nourishnet.controller;

import nourishnet.entity.Diet;
import nourishnet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/diets")
@CrossOrigin(origins = "http://localhost:3000")
public class DietController {
    
    @Autowired
    private DietService dietService;
    
    @GetMapping
    public ResponseEntity<List<Diet>> getAllDiets() {
        List<Diet> diets = dietService.getAllDiets();
        return ResponseEntity.ok(diets);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Diet> getDietById(@PathVariable Long id) {
        return dietService.getDietById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Diet> createDiet(@Valid @RequestBody Diet diet) {
        Diet createdDiet = dietService.createDiet(diet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiet);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Diet> updateDiet(@PathVariable Long id, @Valid @RequestBody Diet diet) {
        try {
            Diet updatedDiet = dietService.updateDiet(id, diet);
            return ResponseEntity.ok(updatedDiet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiet(@PathVariable Long id) {
        dietService.deleteDiet(id);
        return ResponseEntity.noContent().build();
    }
}
