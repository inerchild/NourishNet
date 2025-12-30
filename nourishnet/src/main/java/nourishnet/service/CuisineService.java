package nourishnet.service;

import nourishnet.entity.Cuisine;
import nourishnet.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CuisineService {
    
    @Autowired
    private CuisineRepository cuisineRepository;
    
    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }
    
    public Optional<Cuisine> getCuisineById(Long id) {
        return cuisineRepository.findById(id);
    }
    
    public Optional<Cuisine> getCuisineByName(String name) {
        return cuisineRepository.findByName(name);
    }
    
    public Cuisine createCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }
    
    public Cuisine updateCuisine(Long id, Cuisine cuisineDetails) {
        Cuisine cuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuisine not found with id: " + id));
        
        cuisine.setName(cuisineDetails.getName());
        cuisine.setDescription(cuisineDetails.getDescription());
        cuisine.setRegion(cuisineDetails.getRegion());
        cuisine.setCharacteristics(cuisineDetails.getCharacteristics());
        
        return cuisineRepository.save(cuisine);
    }
    
    public void deleteCuisine(Long id) {
        cuisineRepository.deleteById(id);
    }
}
