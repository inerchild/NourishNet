package nourishnet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cuisines")
public class Cuisine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Cuisine name is required")
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    private String region;
    
    @Column(length = 500)
    private String characteristics;
    
    // Constructors
    public Cuisine() {
    }
    
    public Cuisine(String name, String region) {
        this.name = name;
        this.region = region;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getCharacteristics() {
        return characteristics;
    }
    
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
