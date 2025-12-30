package nourishnet.service;

import nourishnet.entity.Diet;
import nourishnet.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    
    @Autowired
    private DietRepository dietRepository;
    
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }
    
    public Optional<Diet> getDietById(Long id) {
        return dietRepository.findById(id);
    }
    
    public Optional<Diet> getDietByName(String name) {
        return dietRepository.findByName(name);
    }
    
    public Diet createDiet(Diet diet) {
        return dietRepository.save(diet);
    }
    
    public Diet updateDiet(Long id, Diet dietDetails) {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet not found with id: " + id));
        
        diet.setName(dietDetails.getName());
        diet.setDescription(dietDetails.getDescription());
        diet.setGuidelines(dietDetails.getGuidelines());
        diet.setHealthBenefits(dietDetails.getHealthBenefits());
        diet.setRestrictions(dietDetails.getRestrictions());
        
        return dietRepository.save(diet);
    }
    
    public void deleteDiet(Long id) {
        dietRepository.deleteById(id);
    }
}
