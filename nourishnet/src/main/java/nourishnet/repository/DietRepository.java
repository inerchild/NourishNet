package nourishnet.repository;

import nourishnet.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
    
    Optional<Diet> findByName(String name);
    
    boolean existsByName(String name);
}
