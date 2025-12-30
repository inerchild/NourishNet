package nourishnet.repository;

import nourishnet.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    
    List<Content> findByDietIdAndCuisineId(Long dietId, Long cuisineId);
    
    List<Content> findByDietId(Long dietId);
    
    List<Content> findByCuisineId(Long cuisineId);
}
