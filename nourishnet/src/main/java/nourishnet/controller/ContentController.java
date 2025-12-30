package nourishnet.controller;

import nourishnet.entity.Content;
import nourishnet.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {
    
    @Autowired
    private ContentService contentService;
    
    @GetMapping
    public ResponseEntity<List<Content>> getContent(
            @RequestParam(required = false) Long dietId,
            @RequestParam(required = false) Long cuisineId) {
        
        List<Content> content;
        
        if (dietId != null && cuisineId != null) {
            content = contentService.getContentByDietAndCuisine(dietId, cuisineId);
        } else if (dietId != null) {
            content = contentService.getContentByDiet(dietId);
        } else if (cuisineId != null) {
            content = contentService.getContentByCuisine(cuisineId);
        } else {
            content = contentService.getAllContent();
        }
        
        return ResponseEntity.ok(content);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return contentService.getContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Content> createContent(@Valid @RequestBody Content content) {
        Content createdContent = contentService.createContent(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
