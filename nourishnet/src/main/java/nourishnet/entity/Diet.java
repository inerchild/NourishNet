package nourishnet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "diets")
public class Diet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Diet name is required")
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(length = 1000)
    private String guidelines;
    
    @Column(length = 1000)
    private String healthBenefits;
    
    @Column(length = 500)
    private String restrictions;
    
    // Constructors
    public Diet() {
    }
    
    public Diet(String name, String description) {
        this.name = name;
        this.description = description;
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
    
    public String getGuidelines() {
        return guidelines;
    }
    
    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }
    
    public String getHealthBenefits() {
        return healthBenefits;
    }
    
    public void setHealthBenefits(String healthBenefits) {
        this.healthBenefits = healthBenefits;
    }
    
    public String getRestrictions() {
        return restrictions;
    }
    
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }
}
