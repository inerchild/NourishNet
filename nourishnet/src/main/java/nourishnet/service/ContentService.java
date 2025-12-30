package nourishnet.service;

import nourishnet.entity.Content;
import nourishnet.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    
    @Autowired
    private ContentRepository contentRepository;
    
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }
    
    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }
    
    public List<Content> getContentByDietAndCuisine(Long dietId, Long cuisineId) {
        return contentRepository.findByDietIdAndCuisineId(dietId, cuisineId);
    }
    
    public List<Content> getContentByDiet(Long dietId) {
        return contentRepository.findByDietId(dietId);
    }
    
    public List<Content> getContentByCuisine(Long cuisineId) {
        return contentRepository.findByCuisineId(cuisineId);
    }
    
    public Content createContent(Content content) {
        return contentRepository.save(content);
    }
    
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
