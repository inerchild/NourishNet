package nourishnet.repository;

import nourishnet.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    
    List<Content> findByDietIdAndCuisineId(Long dietId, Long cuisineId);
    
    List<Content> findByDietId(Long dietId);
    
    List<Content> findByCuisineId(Long cuisineId);
    
    // Search methods
    @Query("SELECT c FROM Content c WHERE " +
           "LOWER(c.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Content> searchByTitleOrDescription(@Param("searchTerm") String searchTerm);
}
