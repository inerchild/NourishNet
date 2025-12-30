package nourishnet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Content {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Diet ID is required")
    @Column(name = "diet_id", nullable = false)
    private Long dietId;
    
    @NotNull(message = "Cuisine ID is required")
    @Column(name = "cuisine_id", nullable = false)
    private Long cuisineId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Content() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Content(Long dietId, Long cuisineId, String title) {
        this();
        this.dietId = dietId;
        this.cuisineId = cuisineId;
        this.title = title;
    }
    
    // Abstract method to get content type
    public abstract String getContentType();
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getDietId() {
        return dietId;
    }
    
    public void setDietId(Long dietId) {
        this.dietId = dietId;
    }
    
    public Long getCuisineId() {
        return cuisineId;
    }
    
    public void setCuisineId(Long cuisineId) {
        this.cuisineId = cuisineId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
